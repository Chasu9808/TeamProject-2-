package com.busanit501.teamproject2.hjt.service;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface HjtBoardService {
    Long register(HjtBoardDTO hjtBoardDTO);
    HjtBoardDTO read(Long tripBno);
    void update(HjtBoardDTO hjtBoardDTO);
    void delete(Long tripBno);
    void deleteAll(Long tripBno);
    HjtPageResponseDTO<HjtBoardDTO> list(HjtPageRequestDTO hjtPageRequestDTO);
    HjtPageResponseDTO<HjtBoardListReplyCountDTO> listWithReplyCount(HjtPageRequestDTO hjtPageRequestDTO);
    HjtPageResponseDTO<BoardListAllDTO> listWithAll(HjtPageRequestDTO hjtPageRequestDTO);
    default HjtTripBoard dtoToEntity(HjtBoardDTO hjtBoardDTO){
        HjtTripBoard hjtTripBoard = HjtTripBoard.builder()
                .tripBno(hjtBoardDTO.getTripBno())
                .tripTitle(hjtBoardDTO.getTripTitle())
                .tripContent(hjtBoardDTO.getTripContent())
                .tripWriter(hjtBoardDTO.getTripWriter())
                .build();

        // 첨부 이미지들이 추가
        if(hjtBoardDTO.getFileNames() != null) {
            hjtBoardDTO.getFileNames().forEach(fileName ->
            {
                String[] arr = fileName.split("_");
                hjtTripBoard.addImage(arr[0],arr[1]);
            }); // forEach 닫기
        } // if 닫기
        return hjtTripBoard;
    } // dtoToEntity 닫기.

    default HjtBoardDTO entityToDTO(HjtTripBoard hjtTripBoard) {
        HjtBoardDTO hjtBoardDTO = HjtBoardDTO.builder()
                .tripBno(hjtTripBoard.getTripBno())
                .tripTitle(hjtTripBoard.getTripTitle())
                .tripContent(hjtTripBoard.getTripContent())
                .tripWriter(hjtTripBoard.getTripWriter())
                .regDate(hjtTripBoard.getRegDate())
                .modDate(hjtTripBoard.getModDate())
                .build();

        // 첨부된 이미지 파일들.
        List<String> fileNames = hjtTripBoard.getImageSet().stream().sorted().map(
                boardImage -> boardImage.getUuid() + "_" + boardImage.getFileName()
        ).collect(Collectors.toList());

        hjtBoardDTO.setFileNames(fileNames);
        return hjtBoardDTO;
    }
}
