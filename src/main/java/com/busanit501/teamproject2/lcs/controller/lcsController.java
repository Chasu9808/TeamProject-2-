package com.busanit501.teamproject2.lcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lcsController {

    @GetMapping("/lcs/list")
    public String showIndexPage() {
        return "lcs/list";
    }
}