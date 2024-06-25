package com.busanit501.teamproject2.ohj.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.busanit501.teamproject2.ohj.domain.Festival;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Query("select  b from Festival b where b.festivalName  like concat('%',:keyword ,'%')")
    Page<Festival> findByKeyword(String keyword, Pageable pageable);

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Festival b where b.festivalId=:festival_id")
    Optional<Festival> findByIdWithImages(Long festivalId);
}

