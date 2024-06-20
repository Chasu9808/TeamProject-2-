package com.busanit501.teamproject2.lcs.controller;

import com.busanit501.teamproject2.lcs.repository.lcsTripRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lcsTripController {

    private final lcsTripRepository lcsTripRepository;

    public lcsTripController(lcsTripRepository lcsTripRepository) {
        this.lcsTripRepository = lcsTripRepository;
    }

    @GetMapping("/lcs/trip")
    public String index(Model model) {
        model.addAttribute("destinations", lcsTripRepository.findAll());
        return "lcs/trip";
    }
}
