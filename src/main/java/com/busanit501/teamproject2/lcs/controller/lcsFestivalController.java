package com.busanit501.teamproject2.lcs.controller;

import com.busanit501.teamproject2.lcs.domain.lcsFestival;
import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.service.lcsFestivalService;
import com.busanit501.teamproject2.lcs.service.lcsTripService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/lcs")
public class lcsFestivalController {

    @Autowired
    private lcsFestivalService festivalService;

    @GetMapping("/fetch-festivals")
    public String fetchfestivals() {
        festivalService.fetchAndSaveFestivalData();
        return "redirect:/lcs/list";
    }

    @GetMapping("/festival") // 기존의 "/trip"을 "/festival"로 변경
    public String listFestival(Model model) {
        List<lcsFestival> lcsFestivalList = festivalService.getAllFestival();
        model.addAttribute("lcsFestivalList", lcsFestivalList); // lcsFestival을 lcsFestival로 모델에 추가
        return "lcs/festival"; // lcs/festival.html을 반환
    }

    @GetMapping("/festivaldetail/{id}") // 페스티벌 상세 페이지 매핑
    public String festivalDetail(@PathVariable("id") Long id, Model model) {
        lcsFestival festival = festivalService.getFestivalById(id);
        if (festival == null) {
            // 여행 정보가 없을 경우 처리할 예외 처리
            // 여기서는 간단히 404 페이지로 리다이렉트 또는 에러 페이지 표시
            return "redirect:/error"; // 예시로 에러 페이지로 리다이렉트
        }
        model.addAttribute("festival", festival);
        return "lcs/festival-detail"; // lcs/trip-detail.html을 반환
    }

    @GetMapping("/festival-random")
    public String showRandomFestival(Model model) {
        List<lcsFestival> randomFestival = festivalService.getRandomFestivals(3);
        model.addAttribute("randomFestival", randomFestival);
        return "/lcs/festival-random";
    }


}
