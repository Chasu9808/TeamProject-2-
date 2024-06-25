package com.busanit501.teamproject2.hjt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListAllDTO {
    private Long tripBno;
    private String tripTitle;
    private String tripWriter;
    private LocalDateTime regDate;
    private Long tripReplyCount;
    private List<BoardImageDTO> boardImages;
}
