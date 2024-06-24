package com.busanit501.teamproject2.lcs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class lcsFestivalDTO {
    private Long festival_id;
    private String festival_name;
    private String festival_description;
    private String festival_lat;
    private String festival_lng;
    private String festival_address;
    private String festival_day;
    private String festival_imageUrl;
}
