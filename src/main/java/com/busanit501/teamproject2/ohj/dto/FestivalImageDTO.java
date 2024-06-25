package com.busanit501.teamproject2.ohj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FestivalImageDTO {
    private String uuid;
    private String filename;
    private int ord;
}
