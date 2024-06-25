package com.busanit501.teamproject2.lcs.service;

import com.busanit501.teamproject2.lcs.domain.lcsFestival;
import com.busanit501.teamproject2.lcs.repository.lcsFestivalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class lcsFestivalService {

    @Autowired
    private lcsFestivalRepository repository2;

    public void fetchAndSaveFestivalData() {
        String apiUrl2 = "https://apis.data.go.kr/6260000/FestivalService/getFestivalKr";
        String apiKey2 = "YFKD9uxV5cnFGjViao6Fg5bo1Na5MjfFWJ0FKHRSv0A35iCS0o9kjK1/opRCrUBrATa4Hz3p5fmUkUK0kX9yFA==";

        RestTemplate restTemplate2 = new RestTemplate();
        String url2 = apiUrl2 + "?serviceKey=" + apiKey2 + "&pageNo=1&numOfRows=21";
        log.info("Data 처리유무 : " + url2);

        try {
            ResponseEntity<String> responseEntity2 = restTemplate2.getForEntity(url2, String.class);
            String xmlResponse = responseEntity2.getBody();

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonRoot = xmlMapper.readTree(xmlResponse);

            JsonNode jsonItemsNode = jsonRoot.path("body").path("items").path("item");

            // 변환된 json 구조 출력
            System.out.println("JSON 구조: " + jsonRoot);

            // jsonItemsNode 출력
            System.out.println("itemsNode: " + jsonItemsNode);

            List<lcsFestival> festivals = new ArrayList<>();
            for (JsonNode jsonItemNode : jsonItemsNode) {
                lcsFestival festival = lcsFestival.builder()
                        .festival_name(jsonItemNode.path("MAIN_TITLE").asText(null))
                        .festival_description(jsonItemNode.path("ITEMCNTNTS").asText(null))
                        .festival_address(jsonItemNode.path("GUGUN_NM").asText(null))
                        .festival_lat(jsonItemNode.path("LAT").asText(null))
                        .festival_lng(jsonItemNode.path("LNG").asText(null))
                        .festival_imageUrl(jsonItemNode.path("MAIN_IMG_THUMB").asText(null))
                        .festival_day(jsonItemNode.path("USAGE_DAY_WEEK_AND_TIME").asText(null))
                        .build();
                festivals.add(festival);
            }
            repository2.saveAll(festivals);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public List<lcsFestival> getAllFestival() {
        return repository2.findAll();
    }

    public lcsFestival getFestivalById(Long id) {
        return repository2.findById(id).orElse(null);
    }

    public List<lcsFestival> getRandomFestivals(int count) {
        List<lcsFestival> allFestivals = repository2.findAll();
        Collections.shuffle(allFestivals);
        return allFestivals.stream().limit(count).collect(Collectors.toList());
    }
}
