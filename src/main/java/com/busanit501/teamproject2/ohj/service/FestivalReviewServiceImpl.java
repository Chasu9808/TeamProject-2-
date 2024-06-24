package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalReviewServiceImpl implements FestivalReviewService {

    @Autowired
    private FestivalReviewRepository festivalReviewRepository;

    @Override
    public List<FestivalReviewDTO> getReviewsByFestivalId(Long festivalId) {
        // 구현
        return List;
    }

    @Override
    public FestivalReviewDTO addReview(Long festivalId, FestivalReviewDTO reviewDTO) {
        // 구현
        return null;
    }
    // 다른 메서드들...
}