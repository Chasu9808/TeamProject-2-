package com.busanit501.teamproject2.repository;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.repository.HjtBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2

public class HjtBoardRepositoryTest {

    @Autowired
    HjtBoardRepository hjtBoardRepository;

    @Test
    public void hjtTestInsert() {
        IntStream.rangeClosed(1,100).forEach(i ->
                {
                    HjtTripBoard hjtTripBoard = HjtTripBoard.builder()
                            .tripTitle("어디로 가볼까?" + i)
                            .tripContent("여행지" + i)
                            .tripWriter("홍진태"+(i%10))
                            .build();

                    HjtTripBoard result = hjtBoardRepository.save(hjtTripBoard);
                    log.info("삽입한 tripBno: " + result.getTripBno());
                }
        );

    }//hjtTestInsert end

    @Test
    @Transactional
    public void hjtTestSelect() {
        Long tripBno = 1L;
        Optional<HjtTripBoard> result = hjtBoardRepository.findById(tripBno);
        HjtTripBoard hjtTripBoard = result.orElseThrow();
        log.info("결과화면 : " + hjtTripBoard);
    }//testSelect end

    @Test
    @Transactional
    public void hjtTestUpdate() {
        Long tripBno = 1L;
        Optional<HjtTripBoard> result = hjtBoardRepository.findById(tripBno);
        HjtTripBoard hjtTripBoard = result.orElseThrow();

        log.info("조회 결과1 전 : " + hjtTripBoard);
        hjtTripBoard.changeTripTitleAndContent("어디 가볼까 수정","부산진구, 부산사상, 부산기장");
        hjtBoardRepository.save(hjtTripBoard);
        log.info("조회 결과2 후: " + hjtTripBoard);

    }// hjtTestUpdate end

    @Test
    public void hjtTestDelete() {
        Long tripBno = 100L;
        hjtBoardRepository.deleteById(100L);
        log.info("조회 결과2 후: 삭제완료");

    }// hjtTestDelete end

    @Test
    @Transactional
    public void hjtTestPaging() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("tripBno").descending());
        Page<HjtTripBoard> result = hjtBoardRepository.findAll(pageable);

        log.info("전체 갯수 total  result.getTotalElements() : " + result.getTotalElements());
        log.info("전체 페이지  result.getTotalPages() : " + result.getTotalPages());
        log.info("페이지 number  result.getNumber() : " + result.getNumber());
        log.info("페이지 당 불러올 수  result.getSize() : " + result.getSize());
        log.info("불러올 데이터 목록  result.getContent() : " );

        List<HjtTripBoard> list = result.getContent();
        list.forEach(hjtTripBoard -> log.info(hjtTripBoard));
    }// hjtTestPaging end

    @Test
    public void testSearch() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("tripBno").descending());
        // 실행 여부를 확인 해보기.
        hjtBoardRepository.search(pageable);
    }


}
