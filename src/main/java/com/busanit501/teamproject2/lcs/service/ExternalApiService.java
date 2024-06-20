package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.dto.ApiResponse;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class ExternalApiService {

    private final lcsTripRepository tripRepository;

    @Autowired
    public ExternalApiService(lcsTripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void fetchAndSaveTripDataFromApi() {
        try {
            // 대한민국 관광공사 관광지 정보 API 예시
            String apiUrl = "https://apis.data.go.kr/6260000/AttractionService/getAttractionKr?serviceKey=CZCg%2BDiqxG98rGHOe6c4zVq8kDyfNCZhS%2FO98r%2BvIso25SDRurYTDLJIY3gZ5zvFToAmpB6kQPn5vbcsYTOwNg%3D%3D&pageNo=1&numOfRows=10&result=json";

            // RestTemplate을 사용하여 API 호출
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);
            log.info("API Response: " + response);

            // 응답 데이터가 비어 있는지 확인
            if (response == null || response.isEmpty()) {
                log.error("API 응답 데이터가 올바르지 않거나 빈 값입니다.");
                return;
            }

            // XML 응답을 ApiResponse 객체로 변환
            ApiResponse apiResponse;
            try {
                XmlMapper xmlMapper = new XmlMapper();
                apiResponse = xmlMapper.readValue(response, ApiResponse.class);
            } catch (Exception e) {
                log.warn("XML 파싱 실패, JSON 파싱 시도: " + e.getMessage());

                ObjectMapper jsonMapper = new ObjectMapper();
                apiResponse = jsonMapper.readValue(response, ApiResponse.class);
            }

            // 첫 번째 항목을 lcsTrip 객체로 변환하여 저장
            if (apiResponse.getResponseBody() != null && apiResponse.getResponseBody().getItems() != null
                    && apiResponse.getResponseBody().getItems().getItem() != null
                    && !apiResponse.getResponseBody().getItems().getItem().isEmpty()) {

                ApiResponse.Item item = apiResponse.getResponseBody().getItems().getItem().get(0);

                com.busanit501.teamproject2.lcs.domain.lcsTrip trip = new com.busanit501.teamproject2.lcs.domain.lcsTrip();
                trip.setTrip_name(item.getMainTitle());
                trip.setTrip_description(item.getItemCntnts());
                trip.setTrip_lat(item.getLat());
                trip.setTrip_lng(item.getLng());
                trip.setTrip_address(item.getAddr1());
                trip.setTrip_day(item.getUsageDayWeekAndTime());
                trip.setTrip_imageUrl(item.getMainImgThumb());

                // DB에 저장
                tripRepository.save(trip);
            } else {
                log.error("API 응답 데이터에 유효한 항목이 없습니다.");
            }
        } catch (Exception e) {
            log.error("Failed to fetch and save trip data from API", e);
        }
    }
}
