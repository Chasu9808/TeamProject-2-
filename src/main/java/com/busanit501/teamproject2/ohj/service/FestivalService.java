package com.busanit501.teamproject2.ohj.service;

import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.dto.FestivalDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalService {
    @Autowired
    private FestivalRepository festivalRepository;

    public PageResponseDTO<Festival> getAllFestivals(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("festivalName");  // 정렬 기준을 원하는 속성으로 변경할 수 있습니다.
        Page<Festival> result = festivalRepository.findAll(pageable);
        List<Festival> dtoList = result.getContent();
        return PageResponseDTO.<Festival>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    public Optional<Festival> getFestivalById(Long id) {
        return festivalRepository.findById(id);
    }
}