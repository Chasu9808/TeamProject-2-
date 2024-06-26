package com.busanit501.teamproject2.msy.config;

import com.busanit501.teamproject2.msy.security.CustomUserDetailsService;
import com.busanit501.teamproject2.msy.security.handler.CustomSocialLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class msySecurityConfig {
    private final DataSource dataSource;
    private final CustomUserDetailsService customUserDetailsService;

    // 평문 패스워드를 해시 함수 이용해서 인코딩 해주는 도구 주입.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        log.info("시큐리티 동작 확인 ====CustomSecurityConfig======================");
        // 로그인 없이 자동 로그인 확인
        // 빈 설정.
        // 인증 관련된 설정.

        http.formLogin(
                formLogin -> formLogin.loginPage("/msy/login").permitAll()
        );

        // 로그 아웃 설정.
        http.logout(
                logout -> logout.logoutUrl("/msy/logout").logoutSuccessUrl("/msy/login?logout")
        );

        //로그인 후, 성공시 리다이렉트 될 페이지 지정, 간단한 버전.
        http.formLogin(formLogin ->
                formLogin.defaultSuccessUrl("/lcs/list",true)
        );

        // 기본은 csrf 설정이 on, 작업 시에는 끄고 작업하기.
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // 특정 페이지에 접근 권한 설정.
        http.authorizeRequests()
                // 정적 자원 모두 허용.
                .requestMatchers("/css/**", "/js/**","/images/**").permitAll()
                // 리스트는 기본으로 다 들어갈수 있게., 모두 허용
                .requestMatchers("/", "/lcs/list","/msy/join", "/msy/login").permitAll()
                // 로그인 후 확인 하기. 권한 예제) hasRole("USER")
                .requestMatchers("/lcs/detail/**").authenticated()
                //확인용으로 사용하기.
                .anyRequest().permitAll();

        // 자동로그인 설정 1
        http.rememberMe(
                httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                // 토큰 생성시 사용할 암호
                                .key("12345678")
                                // 스프링 시큐리티에서 정의해둔 Repository
                                .tokenRepository(persistentTokenRepository())
                                // UserDetail를 반환하는 사용자가 정의한 클래스
                                .userDetailsService(customUserDetailsService)
                                // 토큰의 만료 시간.
                                .tokenValiditySeconds(60*60*24*30)
        );

        //카카오 로그인 API 설정
        http.oauth2Login(
                // 로그인 후 처리, 적용하기.
                oauthLogin -> oauthLogin.loginPage("/msy/login")
                        .successHandler(authenticationSuccessHandler())
        );

        return http.build();
    }

    // 소셜 로그인 후, 후처리 하는 빈등록.
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    // 자동로그인 설정 2
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    //정적 자원 시큐리티 필터 항목 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}