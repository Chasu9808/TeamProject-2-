package com.busanit501.teamproject2.service;


import com.busanit501.teamproject2.hjt.dto.HjtReplyDTO;
import com.busanit501.teamproject2.hjt.service.HjtReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private HjtReplyService hjtReplyService;

    @Test
    public void testInsert() {



        // 작성할 더미 댓글 필요함.
        HjtReplyDTO hjtReplyDTO = HjtReplyDTO.builder()
                .tripBno(1L)
                .replyText("오늘 점심? 회밀면")
                .replyer("이상용")
                .build();
        Long rno = hjtReplyService.register(hjtReplyDTO);
        log.info("작성한 댓글 번호 : " + rno);
    }

}
