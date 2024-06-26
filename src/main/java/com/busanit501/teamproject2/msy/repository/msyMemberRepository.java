package com.busanit501.teamproject2.msy.repository;

import com.busanit501.teamproject2.msy.domain.msyMember;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface msyMemberRepository extends JpaRepository<msyMember, String> {

    // 소셜 로그인이 아닌, 일반 로그인으로 검색하기.
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from msyMember m where m.mid = :mid and m.social = false")
    Optional<msyMember> getWithRoles(String mid);

    // 이메일으로 유저 확인.
    @EntityGraph(attributePaths = "roleSet")
    Optional<msyMember> findByEmail(String email);

}
