package com.busanit501.teamproject2.hjt.service;

import com.busanit501.teamproject2.hjt.dto.HjtBoardDTO;

public interface HjtBoardService {
    Long register(HjtBoardDTO hjtBoardDTO);
    HjtBoardDTO read(Long tripBno);
    void update(HjtBoardDTO hjtBoardDTO);
    void delete(Long tripBno);
}
