package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.dto.FestivalDTO;
import com.busanit501.teamproject2.ohj.dto.FestivalListAllDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalRepository;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class FestivalServiceImpl  implements FestivalService {

    private final FestivalReviewRepository festivalReviewRepository;
    private final FestivalRepository festivalRepository;
    private final ModelMapper modelMapper;

    @Override
    public FestivalDTO read(Long festivalReviewId) {
        Optional<Festival> result = festivalRepository.findByIdWithImages(festivalReviewId);
        Festival festival =result.orElseThrow();
        FestivalDTO festivalDTO = entityToDTO(festival);
        return festivalDTO;
    }

    @Override
    public PageResponseDTO<FestivalDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");
        // 검색어, 페이징 처리가 된 결과물 10개.
        // VO
        Page<Festival> result = festivalRepository.searchAll(types,keyword,pageable);
        // Entity(VO) -> DTO
        List<FestivalDTO> dtoList = result.getContent().stream()
                .map(festival -> modelMapper.map(festival,FestivalDTO.class))
                .collect(Collectors.toList());

        // 서버 -> 화면에 전달할 준비물 준비 작업 완료.
        // 1)페이지 2) 사이즈 3) 전쳇갯수 4) 검색 결과 내역10개(엔티티-> DTO)
        PageResponseDTO pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
        //test

        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<FestivalListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        return null;
    }
}