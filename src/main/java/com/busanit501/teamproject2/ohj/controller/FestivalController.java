package com.busanit501.teamproject2.ohj.controller;

import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.domain.FestivalReview;
import com.busanit501.teamproject2.ohj.dto.FestivalListAllDTO;
import com.busanit501.teamproject2.ohj.dto.FestivalReviewDTO;
import com.busanit501.teamproject2.ohj.dto.PageRequestDTO;
import com.busanit501.teamproject2.ohj.dto.PageResponseDTO;
import com.busanit501.teamproject2.ohj.repository.FestivalRepository;
import com.busanit501.teamproject2.ohj.repository.FestivalReviewRepository;
import com.busanit501.teamproject2.ohj.service.FestivalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/festival")
@Log4j2
@RequiredArgsConstructor
public class FestivalController {


    private FestivalService festivalService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("BoardController : /board/list  확인 중, pageRequestDTO : " + pageRequestDTO);

        // dto 변경하기, 메서드도 변경하기. 댓글 갯수 포함, 첨부 이미지들 모두 포함.
        PageResponseDTO<FestivalListAllDTO> responseDTO
                = festivalService.listWithAll(pageRequestDTO);

        // 로그인 유저의 , 정보 가져오기.
//        boolean loginCheck = false;
//        memberService.
//        if ()

        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, responseDTO : " + responseDTO);
        log.info("BoardController 확인 중, user : " + user);
        // 서버 -> 화면 데이터 전달.
        model.addAttribute("responseDTO", responseDTO);
        // 로그인 여부에 따라, 로그 아웃 표시하기.
        model.addAttribute("user", user);

    } //list 닫는 부분

}