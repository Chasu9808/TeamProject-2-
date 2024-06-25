package com.busanit501.teamproject2.ohj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
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
public class FestivalListAllDTO {
    private Long festivalId;

    private String festivalName;

    private String festivalLocation;

    private String festivalDescription;

    private List<FestivalImageDTO> imageUrl;


    private LocalDateTime festivalStart;

    private LocalDateTime festivalEnd;

}
