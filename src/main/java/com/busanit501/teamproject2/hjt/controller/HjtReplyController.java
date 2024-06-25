package com.busanit501.teamproject2.hjt.controller;

import com.busanit501.teamproject2.hjt.dto.HjtReplyDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class HjtReplyController {

    @Tag(name = "댓글 등록 post 방식", description = "댓글 등록 post 방식")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> register(@Valid @RequestBody HjtReplyDTO hjtReplyDTO,
                                                     BindingResult bindingResult) throws Exception {
        log.info("ReplyController의 register ,replyDTO 확인: " + hjtReplyDTO);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String,Long> resultMap = Map.of("tripRno",123L);

        return ResponseEntity.ok(resultMap);

    }
}
