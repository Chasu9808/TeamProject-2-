package com.busanit501.teamproject2.hjt.controller;

import com.busanit501.teamproject2.hjt.dto.BoardListAllDTO;
import com.busanit501.teamproject2.hjt.dto.HjtBoardDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageRequestDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageResponseDTO;
import com.busanit501.teamproject2.hjt.service.HjtBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/hjt")
@Log4j2
@RequiredArgsConstructor
public class HjtBoardController {
    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    private final HjtBoardService hjtBoardService;

    @GetMapping("/list")
    public void list(HjtPageRequestDTO hjtPageRequestDTO, Model model) {
        HjtPageResponseDTO<BoardListAllDTO> hjtResponseDTO
                = hjtBoardService.listWithAll(hjtPageRequestDTO);
        hjtPageRequestDTO.setType("t");
        log.info("HjtBoardController hjtPageRequestDTO 확인 :  " + hjtPageRequestDTO);
        log.info("HjtBoardController hjtResponseDTO 확인 :  " + hjtResponseDTO);
        model.addAttribute("hjtResponseDTO", hjtResponseDTO);
        model.addAttribute("hjtPageRequestDTO", hjtPageRequestDTO);
    }

    @GetMapping("/register")
    public void registerForm() {

    }

    @PostMapping("/register")
    public String register(HjtBoardDTO hjtBoardDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("register 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            return "redirect:/hjt/register";
        }
        log.info("화면에서 입력 받은 내용 확인 : " + hjtBoardDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        Long tripBno = hjtBoardService.register(hjtBoardDTO);

        redirectAttributes.addFlashAttribute("result", tripBno);
        return "redirect:/hjt/list";
    }

    @GetMapping({"/read", "/update"})
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
            , HjtPageRequestDTO hjtPageRequestDTO) {

        if (bindingResult.hasErrors()) {
            log.info("update 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("tripBno", hjtBoardDTO.getTripBno());
            return "redirect:/hjt/update" + hjtPageRequestDTO.getLink();
        }
        log.info("화면에서 입력 받은 내용 update 확인 : " + hjtBoardDTO);
        hjtBoardService.update(hjtBoardDTO);

        redirectAttributes.addFlashAttribute("result", hjtBoardDTO.getTripBno());
        redirectAttributes.addFlashAttribute("resultType", "update");
        return "redirect:/hjt/list?" + hjtPageRequestDTO.getLink2();

    }

    //글삭제 처리
    @PostMapping("/delete")
    public String delete(HjtBoardDTO hjtBoardDTO, HjtPageRequestDTO hjtPageRequestDTO, Long tripBno,
                         RedirectAttributes redirectAttributes
    ) {
        hjtBoardService.deleteAll(tripBno);
        List<String> fileNames = hjtBoardDTO.getFileNames();
        if (fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", tripBno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/hjt/list?" + hjtPageRequestDTO.getLink2();

    }

    public void removeFiles(List<String> files) {
        for (String fileName : files) {
            Resource resource = new FileSystemResource(
                    uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            boolean deleteCheck = false;
            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                deleteCheck = resource.getFile().delete();
                if (contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator
                            + "s_" + fileName);
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
