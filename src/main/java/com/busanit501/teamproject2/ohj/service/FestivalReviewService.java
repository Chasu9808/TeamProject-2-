package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;

import java.util.List;

public interface FestivalReviewService {
    List<FestivalReviewDTO> getReviewsByFestivalId(Long festivalId);
    FestivalReviewDTO addReview(Long festivalId, FestivalReviewDTO reviewDTO);
    // 다른 메서드들...
}
