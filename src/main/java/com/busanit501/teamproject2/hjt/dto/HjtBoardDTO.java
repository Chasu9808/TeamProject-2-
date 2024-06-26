package com.busanit501.teamproject2.hjt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class HjtBoardDTO {
    private Long tripBno;
    @NotEmpty
    @Size(min = 3, max = 100)
    private String tripTitle;
    @NotEmpty
    private String tripContent;
    @NotEmpty
    private String tripWriter;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<String> fileNames;
}
