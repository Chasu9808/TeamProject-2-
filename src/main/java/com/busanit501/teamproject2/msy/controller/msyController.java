package com.busanit501.teamproject2.msy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class msyController {

    @GetMapping("/msy/list")
    public String showIndexPage() {
        return "msy/list";
    }
}
