package com.busanit501.teamproject2.ohj.service;


import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import com.busanit501.teamproject2.ohj.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public interface FestivalService {

    FestivalDTO read(Long festivalReviewId);

    PageResponseDTO<FestivalDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<FestivalListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    default Festival dtoToEntity(FestivalDTO festivalDTO){

        Festival festival = Festival.builder()
                .festivalId(festivalDTO.getFestivalId())
                .festivalName(festivalDTO.getFestivalName())
                .festivalDescription(festivalDTO.getFestivalDescription())
                .festivalLocation(festivalDTO.getFestivalLocation())
                .festivalStart(LocalDate.from(festivalDTO.getFestivalStart()))
                .festivalEnd(LocalDate.from(festivalDTO.getFestivalEnd()))
                .build();

        // 첨부 이미지들이 추가
        if(festivalDTO.getFileNames() != null) {
            festivalDTO.getFileNames().forEach(fileName ->
            {
                String[] arr = fileName.split("_");
                festival.addImage(arr[0],arr[1]);
            }); // forEach 닫기
        } // if 닫기
        return festival;
    } // dtoToEntity 닫기.

    // entityToDTO
    // 화면(DTO) ->  컨트롤러 ->서비스(각 변환작업을함.) - Entity 타입으로 - DB
    default FestivalDTO entityToDTO(Festival festival) {
        FestivalDTO festivalDTO = FestivalDTO.builder()
                .festivalId(festival.getFestivalId())
                .festivalName(festival.getFestivalName())
                .festivalDescription(festival.getFestivalDescription())
                .festivalLocation(festival.getFestivalLocation())
                .festivalStart(festival.getFestivalStart().atStartOfDay())
                .festivalEnd(festival.getFestivalEnd().atStartOfDay())
                .build();

        // 첨부된 이미지 파일들.
        List<String> fileNames = festival.getImageSet().stream().sorted().map(
                boardImage -> boardImage.getUuid()+"_"+boardImage.getFileName()
        ).collect(Collectors.toList());

        festivalDTO.setFileNames(fileNames);

        return festivalDTO;
    }
}