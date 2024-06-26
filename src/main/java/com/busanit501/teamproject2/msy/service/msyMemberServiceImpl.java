package com.busanit501.teamproject2.msy.service;

import com.busanit501.teamproject2.msy.domain.msyMember;
import com.busanit501.teamproject2.msy.dto.msyMemberDTO;
import com.busanit501.teamproject2.msy.repository.msyMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class msyMemberServiceImpl implements msyMemberService {

    private final msyMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(msyMemberDTO memberDTO) throws MidExistException {
        //기존 아이디와 중복되는지 여부 확인
        String mid = memberDTO.getMid();
        boolean existMember = memberRepository.existsById(mid);
        if (existMember) {
            throw new MidExistException();
        }
        // 중복이 아니니 회원 가입 처리하기.
        msyMember member = dtoToEntity(memberDTO);
        //패스워드는 현재 평문 -> 암호로 변경.
        member.changePassword(passwordEncoder.encode(member.getMpw()));

        memberRepository.save(member);
    }

}
