package com.busanit501.teamproject2.ohj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.busanit501.teamproject2.ohj.domain.Festival;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
}

