package com.busanit501.teamproject2.ohj.controller;

import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalRepository;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import com.busanit501.teamproject2.ohj.service.FestivalService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ohj")
public class FestivalController {
    @Autowired
    private FestivalService festivalService;

    @GetMapping("/festivals")
    public PageResponseDTO<Festival> getAllFestivals(PageRequestDTO pageRequestDTO) {
        return festivalService.getAllFestivals(pageRequestDTO);
    }

    @GetMapping("/festival/{id}")
    public Optional<Festival> getFestivalById(@PathVariable Long id) {
        return festivalService.getFestivalById(id);
    }
}