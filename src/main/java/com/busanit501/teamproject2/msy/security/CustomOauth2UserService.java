package com.busanit501.teamproject2.msy.security;

import com.busanit501.teamproject2.msy.domain.msyMember;
import com.busanit501.teamproject2.msy.repository.msyMemberRepository;
import com.busanit501.teamproject2.msy.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final msyMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 카카오 소셜 로그인 로직 처리
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // userRequest, 카카오 로그인 관련 정보
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        paramMap.forEach((k, v) -> {
            log.info("CustomOauth2UserService : k = " + k + " v = " + v);
        });

        String email = null;

        switch (clientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        return generateDTO(email, paramMap);
    }

    private MemberSecurityDTO generateDTO(String email, Map<String, Object> paramMap) {
        Optional<msyMember> result = memberRepository.findByEmail(email);
        //디비에 유저가 없다면, 소셜로그인. (이메일포함)
        // 일반 로그인으로 로그인시 (가입한 이메일)
        if (result.isEmpty()) {
            // 회원 추가 하기, mid: 이메일, 패스워드 : 임시로 1234
            msyMember member = msyMember.builder()
                    .mid(email)
                    .mpw(passwordEncoder.encode("1234"))
                    .email(email)
                    .build();

            memberRepository.save(member);

            // entitty -> DTO
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(email, "1234", "이희지", email, "010-1234-5678",  Arrays.asList(
                            new SimpleGrantedAuthority("ROLE_USER")));
            memberSecurityDTO.setProps(paramMap);
            return memberSecurityDTO;
        } // 소셜 로그인 한 정보의 이메일이 디비에 없을 경우
        // 직접 로그인한 정보가 있다, 디비에 소셜 로그인한 이메일이 존재 한다면
        else {
            msyMember member = result.get();
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(
                            member.getMid(),
                            member.getMpw(),
                            member.getName(),
                            member.getEmail(),
                            member.getPhone(),
                            member.getRoleSet().stream().map(
                                    memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())

                            ).collect(Collectors.toList())
                    );
            return memberSecurityDTO;
        }
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {
        Object value = paramMap.get("kakao_account");

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String) accountMap.get("email");
        return email;
    }

}