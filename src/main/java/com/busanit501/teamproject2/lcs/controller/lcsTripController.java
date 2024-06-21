package com.busanit501.teamproject2.lcs.controller;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import com.busanit501.teamproject2.lcs.service.lcsTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}