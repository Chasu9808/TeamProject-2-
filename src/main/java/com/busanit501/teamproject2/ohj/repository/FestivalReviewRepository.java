package com.busanit501.teamproject2.ohj.repository;

import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivalReviewRepository extends JpaRepository<FestivalReview, Long> {
    List<FestivalReview> findByFestivalDestinationId(Long festivalDestinationId);
}

