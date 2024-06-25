package com.busanit501.teamproject2.hjt.repository;

import com.busanit501.teamproject2.hjt.domain.HjtTripReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HjtReplyRepository extends JpaRepository<HjtTripReply, Long> {
    @Query("select r from HjtTripReply r where r.hjtTripBoard.tripBno = :tripBno")
    Page<HjtTripReply> listOfBoard(Long tripBno, Pageable pageable);

    void deleteByHjtTripBoard_TripBno(Long tripBno);
    List<HjtTripReply> findByHjtTripBoard_TripBno(Long tripBno);
}
