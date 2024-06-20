package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class lcsTripService {

    @Autowired
    private lcsTripRepository repository;

    public void fetchAndSaveTripData() {
        String apiUrl = "http://apis.data.go.kr/6260000/AttractionService"; // 공공데이터 포털 API URL
        String apiKey = "CZCg%2BDiqxG98rGHOe6c4zVq8kDyfNCZhS%2FO98r%2BvIso25SDRurYTDLJIY3gZ5zvFToAmpB6kQPn5vbcsYTOwNg%3D%3D"; // 공공데이터 포털에서 발급받은 API 키

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?serviceKey=" + apiKey + "&format=json";
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(response);
            List<lcsTrip> trips = new ArrayList<>();
            for (JsonNode node : root.path("items")) {
                lcsTrip trip = lcsTrip.builder()
                        .trip_name(node.path("MAIN_TITLE").asText())
                        .trip_description(node.path("ITEMCNTNTS").asText())
                        .trip_lat(node.path("LAT").asText())
                        .trip_lng(node.path("LNG").asText())
                        .trip_address(node.path("ADDR1").asText())
                        .trip_day(node.path("USAGE_DAY_WEEK_AND_TIME").asText())
                        .trip_imageUrl(node.path("MAIN_IMG_THUMB").asText())
                        .build();
                trips.add(trip);
            }
            repository.saveAll(trips);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<lcsTrip> getAllTrips() {
        return repository.findAll();
    }
}
