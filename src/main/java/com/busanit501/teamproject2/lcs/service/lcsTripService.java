package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class lcsTripService {

    @Autowired
    private lcsTripRepository repository;

    public void fetchAndSaveTripData() {
        String apiUrl = "https://apis.data.go.kr/6260000/AttractionService/getAttractionKr";
        String apiKey = "ViNhf9KFHrhlepP82G2riFCwzySQxL4juLIE5itFGrf8lpCixgdypSpsC930g7033hqAO8PKM99K5eNbt13uSA==";

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?serviceKey=" + apiKey + "&pageNo=1&numOfRows=20";

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String response = responseEntity.getBody();

            XmlMapper mapper = new XmlMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode itemsNode = root.path("body").path("items").path("item");

            // 변환된 json 구조
            System.out.println("JSON 구조: " + root);

            // itemsNode 출력
            System.out.println("itemsNode: " + itemsNode);

            List<lcsTrip> trips = new ArrayList<>();
            for (JsonNode node : itemsNode) {
                lcsTrip trip = lcsTrip.builder()
                        .trip_name(node.path("MAIN_TITLE").asText(null))
                        .trip_description(node.path("ITEMCNTNTS").asText(null))
                        .trip_address(node.path("ADDR1").asText(null))
                        .trip_lat(node.path("LAT").asText(null))
                        .trip_lng(node.path("LNG").asText(null))
                        .trip_imageUrl(node.path("MAIN_IMG_THUMB").asText(null))
                        .trip_day(node.path("USAGE_DAY_WEEK_AND_TIME").asText(null))

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

    public lcsTrip getTripById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<lcsTrip> getRandomTrips(int count) {
        List<lcsTrip> allTrips = repository.findAll();
        Collections.shuffle(allTrips);
        return allTrips.stream().limit(count).collect(Collectors.toList());
    }
}
