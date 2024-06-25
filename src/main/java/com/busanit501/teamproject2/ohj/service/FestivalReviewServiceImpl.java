package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalReviewServiceImpl implements FestivalReviewService {


    @Override
    public Long register(FestivalReviewDTO festivalReviewDTO) {
        return 0;
    }

    @Override
    public FestivalReviewDTO read(Long festivalReviewId) {
        return null;
    }

    @Override
    public void update(FestivalReviewDTO festivalReviewDTO) {

    }

    @Override
    public void delete(Long festivalReviewId) {

    }

    @Override
    public PageResponseDTO<FestivalReviewDTO> getListOfBoard(Long festivalReviewId, PageRequestDTO pageRequestDTO) {
        return null;
    }
}