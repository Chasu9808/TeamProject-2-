package com.busanit501.teamproject2.lcs.repository;

import com.busanit501.teamproject2.lcs.domain.lcsTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface lcsTripRepository extends JpaRepository<lcsTrip, Long> {
}
