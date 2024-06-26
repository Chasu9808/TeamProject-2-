package com.busanit501.teamproject2.hjt.service;

import com.busanit501.teamproject2.hjt.dto.HjtPageRequestDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageResponseDTO;
import com.busanit501.teamproject2.hjt.dto.HjtReplyDTO;

public interface HjtReplyService {
    Long register(HjtReplyDTO hjtReplyDTO);
    HjtReplyDTO read(Long tripRno);
    void update(HjtReplyDTO hjtReplyDTO);
    void delete(Long tripRno);
    HjtPageResponseDTO<HjtReplyDTO> getListOfHjtTripBoard(Long tripRno, HjtPageRequestDTO hjtPageRequestDTO);
}
