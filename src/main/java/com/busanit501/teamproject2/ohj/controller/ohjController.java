package com.busanit501.teamproject2.ohj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ohjController {

    @GetMapping("/ohj/list")
    public String showIndexPage() {
        return "ohj/list";
    }
}
