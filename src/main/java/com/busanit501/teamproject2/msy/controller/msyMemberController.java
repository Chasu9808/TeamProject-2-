package com.busanit501.teamproject2.msy.controller;

import com.busanit501.teamproject2.msy.dto.msyMemberDTO;
import com.busanit501.teamproject2.msy.service.msyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/msy")
@Log4j2
@RequiredArgsConstructor
public class msyMemberController {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String restAPIKEY;

    private final msyMemberService memberService;

    @GetMapping("/kakaoLogout")
    public String kakaoLogout() {
        return "redirect:https://kauth.kakao.com/oauth/logout?client_id="+restAPIKEY+"&logout_redirect_uri=http://localhost:8080/msy/login";
    }

    @GetMapping("/login")
    public void loginGet() {

    }

    @GetMapping("/join")
    public void joinGet() {

    }

    // 회원 가입 로직 처리
    @PostMapping("/join")
    public String joinPost(msyMemberDTO memberDTO,
                           RedirectAttributes redirectAttributes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(msyMemberDTO.builder().birth(memberDTO.getBirth()).toString(), formatter);
        LocalDateTime birthDateTime = birthDate.atStartOfDay();
        try {
            memberService.join(memberDTO);
        } catch (msyMemberService.MidExistException e) {
            redirectAttributes.addFlashAttribute("error", "아이디 중복입니다");
            return "redirect:/msy/join";
        }
        redirectAttributes.addFlashAttribute("result","회원가입 성공");
        return "redirect:/msy/login";
    }

}