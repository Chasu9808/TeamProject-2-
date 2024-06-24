package com.busanit501.teamproject2.hjt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HjtBoardDTO {
    private Long tripBno;
    private String tripTitle;
    private String tripContent;
    private String tripWriter;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
