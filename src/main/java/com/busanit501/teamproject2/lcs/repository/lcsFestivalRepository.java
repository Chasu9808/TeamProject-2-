package com.busanit501.teamproject2.lcs.repository;

import com.busanit501.teamproject2.lcs.domain.lcsFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface lcsFestivalRepository extends JpaRepository<lcsFestival, Long> {
}
