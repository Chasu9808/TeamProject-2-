package com.busanit501.teamproject2.msy.service;

import com.busanit501.teamproject2.msy.domain.msyMember;
import com.busanit501.teamproject2.msy.dto.msyMemberDTO;

public interface msyMemberService {

    // 중복 아이디 예외처리
    static class MidExistException extends Exception {

    }

    void join(msyMemberDTO memberDTO) throws MidExistException;

    default msyMember dtoToEntity(msyMemberDTO memberDTO){
        msyMember member = msyMember.builder()
                .mid(memberDTO.getMid())
                .mpw(memberDTO.getMpw())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .build();

        return member;
    }

    default msyMemberDTO entityToDTO(msyMember member) {
        msyMemberDTO memberDTO = msyMemberDTO.builder()
                .mid(member.getMid())
                .mpw(member.getMpw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();

        return memberDTO;
    }

}
