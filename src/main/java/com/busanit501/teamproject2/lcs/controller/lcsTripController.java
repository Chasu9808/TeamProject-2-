package com.busanit501.teamproject2.lcs.controller;

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

    @GetMapping("/trip")
    public String index(Model model) {
        model.addAttribute("lcsTripList", tripService.getAllTrips());
        return "lcs/trip";
    }
}
