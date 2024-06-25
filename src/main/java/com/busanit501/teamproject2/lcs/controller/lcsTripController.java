package com.busanit501.teamproject2.lcs.controller;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.service.lcsTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/lcs")
public class lcsTripController {

    @Autowired
    private lcsTripService tripService;

    @GetMapping("/fetch-trips")
    public String fetchTrips() {
        tripService.fetchAndSaveTripData();
        return "redirect:/lcs/list";
    }

    @GetMapping("/trip") // 기존의 "/trip"을 "/list"로 변경
    public String listTrips(Model model) {
        List<lcsTrip> lcsTripList = tripService.getAllTrips();
        model.addAttribute("lcsTripList", lcsTripList); // trips를 lcsTripList로 모델에 추가
        return "lcs/trip"; // lcs/trip.html을 반환
    }

    @GetMapping("/detail/{id}") // 여행 상세 페이지 매핑
    public String tripDetail(@PathVariable("id") Long id, Model model) {
        lcsTrip trip = tripService.getTripById(id);
        if (trip == null) {
            // 여행 정보가 없을 경우 처리할 예외 처리
            // 여기서는 간단히 404 페이지로 리다이렉트 또는 에러 페이지 표시
            return "redirect:/error"; // 예시로 에러 페이지로 리다이렉트
        }
        model.addAttribute("trip", trip);
        return "lcs/trip-detail"; // lcs/trip-detail.html을 반환
    }

    @GetMapping("/trip-random")
    public String showRandomTrips(Model model) {
        List<lcsTrip> randomTrip = tripService.getRandomTrips(3);
        model.addAttribute("randomTrip", randomTrip);
        return "/lcs/trip-random";
    }


}
