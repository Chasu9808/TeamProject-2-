package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.dto.FestivalDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    @Override
    public List<FestivalDTO> getAllFestivals() {
        // 구현
        return null
    }

    @Override
    public FestivalDTO getFestivalById(Long id) {
        // 구현
    }

    @Override
    public FestivalDTO createFestival(FestivalDTO festivalDTO) {
        // 구현
    }
    // 다른 메서드들...
}