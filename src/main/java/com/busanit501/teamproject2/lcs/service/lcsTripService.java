package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.dto.lcsTripDTO;
import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class lcsTripService {

    @Autowired
    private lcsTripRepository repository;

    public void fetchAndSaveTripData() {
        String apiUrl = "http://apis.data.go.kr/6260000/AttractionService"; // 공공데이터 포털 API URL
        String apiKey = "CZCg+DiqxG98rGHOe6c4zVq8kDyfNCZhS/O98r+vIso25SDRurYTDLJIY3gZ5zvFToAmpB6kQPn5vbcsYTOwNg=="; // 공공데이터 포털에서 발급받은 API 키

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?serviceKey=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(response);
            List<lcsTrip> trips = new ArrayList<>();
            for (JsonNode node : root.path("items")) {
                lcsTrip trip = lcsTrip.builder()
                        .trip_name(node.path("name").asText())
                        .trip_description(node.path("description").asText())
                        .trip_location(node.path("location").asText())
                        .trip_rating(node.path("rating").asText())
                        .trip_category(node.path("category").asText())
                        .trip_recommended(node.path("recommended").asBoolean())
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
