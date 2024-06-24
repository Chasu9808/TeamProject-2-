package com.busanit501.teamproject2.ohj.controller;

import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivals")
public class FestivalReviewController {

    @Autowired
    private FestivalReviewRepository festivalReviewRepository;

    @GetMapping("/{id}/reviews")
    public List<FestivalReview> getFestivalReviews(@PathVariable Long id) {
        return festivalReviewRepository.findByFestivalDestinationId(id);
    }

    @PostMapping("/{id}/reviews")
    public FestivalReview addFestivalReview(@PathVariable Long id, @RequestBody FestivalReview festivalReview) {
        festivalReview.setFestivalReviewId(id);
        return festivalReviewRepository.save(festivalReview);
    }
}
