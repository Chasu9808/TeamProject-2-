package com.busanit501.teamproject2.hjt.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HjtBoardListReplyCountDTO {
    private Long tripBno;
    private String tripTitle;
    private String tripWriter;
    private LocalDateTime regDate;
    private Long tripReplyCount;
}
