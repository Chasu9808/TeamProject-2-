package com.busanit501.teamproject2.lcs.controller;

import com.busanit501.teamproject2.lcs.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class lcsAPIController {

    private final ExternalApiService externalApiService;

    @Autowired
    public lcsAPIController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/trips")
    public ResponseEntity<String> createTripFromApi() {
        try {
            // 외부 API에서 관광지 데이터 가져와서 DB에 저장
            externalApiService.fetchAndSaveTripDataFromApi();
            return ResponseEntity.status(HttpStatus.CREATED).body("Trip saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save trip");
        }
    }
}
