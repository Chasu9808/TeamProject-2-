package com.busanit501.teamproject2.msy.security;

import com.busanit501.teamproject2.msy.domain.msyMember;
import com.busanit501.teamproject2.msy.repository.msyMemberRepository;
import com.busanit501.teamproject2.msy.security.dto.MemberSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private msyMemberRepository memberRepository;

    // 로그인 로직 처리시, 여기를 반드시 거쳐 감.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<msyMember> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("유저가 존재하지 않습니다.");
        }
        // 디비에 해당 유저가 있다면, 이어서 로그인 처리하기.
        msyMember member = result.get();

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMid(),
                member.getMpw(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.isDel(),
                false,
                member.getRoleSet().stream().map(
                        memberRole -> new SimpleGrantedAuthority("ROLE_"+ memberRole.name())
                ).collect(Collectors.toList())
        );

        return memberSecurityDTO;
    }
}
