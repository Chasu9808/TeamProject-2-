package com.busanit501.teamproject2.msy.security.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User implements OAuth2User {
    private String mid;
    private String mpw;
    private String name;
    private String email;
    private String phone;
    //소셜 로그인 정보
    private Map<String, Object> props;

    //생성자
    public MemberSecurityDTO(
            //로그인한 유저이름.
            String username,String password,String name,
            String email, String phone,
            //GrantedAuthority 를 상속한 클래스는 아무나 올 수 있다. 타입으로
            Collection<? extends GrantedAuthority> authorities
    ){
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // 카카오 인증 연동시, 필수 재정의 메서드
    @Override
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    @Override
    public String getName() {
        return this.mid;
    }
}
