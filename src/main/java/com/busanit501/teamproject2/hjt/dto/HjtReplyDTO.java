package com.busanit501.teamproject2.hjt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HjtReplyDTO {
    private Long tripRno;
    @NotNull
    private Long tripBno;
    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;

    private LocalDateTime regDate, modDate;
}
