package com.busanit501.teamproject2.hjt.service;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.dto.HjtBoardDTO;
import com.busanit501.teamproject2.hjt.repository.HjtBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class HjtBoardServiceImpl implements HjtBoardService {
    private final HjtBoardRepository hjtBoardRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long register(HjtBoardDTO hjtBoardDTO) {
        HjtTripBoard hjtTripBoard = modelMapper.map(hjtBoardDTO, HjtTripBoard.class);
        Long tripBno = hjtBoardRepository.save(hjtTripBoard).getTripBno();
        return tripBno;
    }

    @Override
    public HjtBoardDTO read(Long tripBno) {
        Optional<HjtTripBoard> result = hjtBoardRepository.findById(tripBno);
        HjtTripBoard hjtTripBoard = result.orElseThrow();
        HjtBoardDTO hjtBoardDTO = modelMapper.map(hjtTripBoard, HjtBoardDTO.class);
        return null;
    }

    @Override
    public void update(HjtBoardDTO hjtBoardDTO) {
        Optional<HjtTripBoard> result = hjtBoardRepository.findById(hjtBoardDTO.getTripBno());
        HjtTripBoard hjtTripBoard = result.orElseThrow();
        hjtTripBoard.changeTripTitleAndContent(hjtBoardDTO.getTripTitle(),hjtBoardDTO.getTripContent());
        hjtBoardRepository.save(hjtTripBoard);
    }

    @Override
    public void delete(Long tripBno) {
        hjtBoardRepository.deleteById(tripBno);
    }
}
