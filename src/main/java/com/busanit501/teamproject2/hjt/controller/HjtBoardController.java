package com.busanit501.teamproject2.hjt.controller;

import com.busanit501.teamproject2.hjt.dto.HjtBoardDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageRequestDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageResponseDTO;
import com.busanit501.teamproject2.hjt.service.HjtBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/hjtboard")
@Log4j2
@RequiredArgsConstructor
public class HjtBoardController {
    private final HjtBoardService hjtBoardService;

    @GetMapping("/list")
    public void list(HjtPageRequestDTO hjtPageRequestDTO, Model model) {
        HjtPageResponseDTO<HjtBoardDTO> hjtResponseDTO = hjtBoardService.list(hjtPageRequestDTO);
        model.addAttribute("hjtResponseDTO", hjtResponseDTO);
    }

    @GetMapping("/register")
    public void registerForm() {

    }

    @PostMapping("/register")
    public String register(HjtBoardDTO hjtBoardDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            log.info("register 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info("화면에서 입력 받은 내용 확인 : " + hjtBoardDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        Long tripBno = hjtBoardService.register(hjtBoardDTO);

        redirectAttributes.addFlashAttribute("result",tripBno);
        return "redirect:/hjtboard/list";
    }

    @GetMapping({"/read","/update"})
    public void read(Long tripBno, HjtPageRequestDTO hjtPageRequestDTO, Model model) {

        log.info("BoardController : /board/read  확인 중, pageRequestDTO : " + hjtPageRequestDTO);

        HjtBoardDTO hjtBoardDTO = hjtBoardService.read(tripBno);
        log.info("HjtBoardController 확인 중, hjtBoardDTO : " + hjtBoardDTO);
        model.addAttribute("hjtBoardDTO", hjtBoardDTO);

    }

    @PostMapping("/update")
    public String update(@Valid HjtBoardDTO hjtBoardDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model
            ,HjtPageRequestDTO hjtPageRequestDTO) {
        // 입력중 유효성 체크에 해당 될 때
        if(bindingResult.hasErrors()) {
            log.info("update 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            // 서버 -> 화면으로 쿼리 스트링으로전달.
            // 예시 : ?bno=게시글번호
            redirectAttributes.addAttribute("tripBno", hjtBoardDTO.getTripBno());
            return "redirect:/hjtboard/update"+hjtPageRequestDTO.getLink();
        }
        log.info("화면에서 입력 받은 내용 update 확인 : " + hjtBoardDTO);
        hjtBoardService.update(hjtBoardDTO);

        redirectAttributes.addFlashAttribute("result",hjtBoardDTO.getTripBno());
        redirectAttributes.addFlashAttribute("resultType","update");
        return "redirect:/hjtboard/list?" + hjtPageRequestDTO.getLink2();

    }

    //글삭제 처리
    @PostMapping("/delete")
    public String delete(HjtPageRequestDTO hjtPageRequestDTO, Long tripBno, RedirectAttributes redirectAttributes
    ) {
        hjtBoardService.delete(tripBno);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result",tripBno);
        redirectAttributes.addFlashAttribute("resultType","delete");
        return "redirect:/hjtboard/list?"+hjtPageRequestDTO.getLink2();

    }


}
