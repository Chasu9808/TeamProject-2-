package com.busanit501.teamproject2.lhj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lhjController {

    @GetMapping("/lhj/list")
    public String showIndexPage() {
        return "lhj/list";
    }
}
