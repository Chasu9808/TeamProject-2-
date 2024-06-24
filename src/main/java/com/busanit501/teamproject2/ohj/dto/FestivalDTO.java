package com.busanit501.teamproject2.ohj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FestivalDTO {

    private Long festivalId;
    @NotNull
    private String festivalName;

    private String festivalLocation;

    private String festivalDescription;

    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime festivalStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime festivalEnd;

    // Getters and setters
}
