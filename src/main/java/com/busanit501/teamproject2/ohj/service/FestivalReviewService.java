package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;

import java.util.List;

public interface FestivalReviewService {
    Long register(FestivalReviewDTO festivalReviewDTO);
    FestivalReviewDTO read(Long festivalReviewId);
    void update(FestivalReviewDTO festivalReviewDTO);
    void delete(Long festivalReviewId);
    PageResponseDTO<FestivalReviewDTO> getListOfBoard(Long festivalReviewId, PageRequestDTO pageRequestDTO);
}
