package com.busanit501.teamproject2.hjt.controller;

import com.busanit501.teamproject2.hjt.dto.HjtPageRequestDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageResponseDTO;
import com.busanit501.teamproject2.hjt.dto.HjtReplyDTO;
import com.busanit501.teamproject2.hjt.service.HjtReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class HjtReplyController {
    private final HjtReplyService hjtReplyService;

    @Tag(name = "댓글 등록 post 방식", description = "댓글 등록 post 방식")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(@Valid @RequestBody HjtReplyDTO hjtReplyDTO,
                                                     BindingResult bindingResult) throws Exception {
        log.info("ReplyController의 register ,replyDTO 확인: " + hjtReplyDTO);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String,Long> resultMap = new HashMap<>();
        Long tripRno = hjtReplyService.register(hjtReplyDTO);
        resultMap.put("tripRno", tripRno);

        return resultMap;
    }

    @Tag(name = "댓글 목록 조회 get 방식", description = "댓글 목록 조회 get 방식")
    @GetMapping(value = "/list/{tripBno}")
    public HjtPageResponseDTO<HjtReplyDTO> getList(

            @PathVariable("tripBno") Long tripBno, HjtPageRequestDTO hjtPageRequestDTO
    ) {
        HjtPageResponseDTO<HjtReplyDTO> hjtResponseDTO = hjtReplyService.getListOfHjtTripBoard(tripBno, hjtPageRequestDTO);
        return  hjtResponseDTO;
    }

    @Tag(name = "특정 댓글 한개 조회 get 방식", description = "특정 댓글 한개 조회 get 방식")
    @GetMapping(value = "/{tripRno}")
    public HjtReplyDTO getReadOne(
            @PathVariable("tripRno") Long tripRno
    ) {
        HjtReplyDTO hjtReplyDTO = hjtReplyService.read(tripRno);
        return  hjtReplyDTO;
    } //getList

    @Tag(name = "특정 댓글 삭제 delete 방식", description = "특정 댓글 삭제 delete 방식")
    @DeleteMapping(value = "/{tripRno}")
    public Map<String,Long> delete(
            @PathVariable("tripRno") Long tripRno
    ) {
        hjtReplyService.delete(tripRno);
        Map<String,Long> resultMap = new HashMap<>();
        resultMap.put("tripRno",tripRno);
        return  resultMap;
    } //getList

    @Tag(name = "특정 댓글 수정 put 방식", description = "특정 댓글 수정 put 방식")
    @PutMapping(value = "/{tripRno}", consumes = MediaType.APPLICATION_JSON_VALUE)

    public Map<String,Long> update(
            @PathVariable("tripRno") Long tripRno,
            @Valid @RequestBody HjtReplyDTO hjtReplyDTO,
            BindingResult bindingResult) throws BindException {
        log.info("HjtReplyController의 update , hjtReplyDTO 확인: " + hjtReplyDTO);

        hjtReplyDTO.setTripRno(tripRno);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String,Long> resultMap = new HashMap<>();
        hjtReplyService.update(hjtReplyDTO);

        resultMap.put("tripRno",tripRno);
        return resultMap;

    } // register 닫는 부분


}
