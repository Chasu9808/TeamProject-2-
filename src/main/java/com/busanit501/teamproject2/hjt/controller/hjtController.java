package com.busanit501.teamproject2.hjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hjtController {

    @GetMapping("/hjt/list")
    public String showIndexPage() {
        return "hjt/list";
    }
}
