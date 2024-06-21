package com.busanit501.teamproject2.lcs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class lcsTripDTO {
    private Long trip_id;
    private String trip_name;
    private String trip_description;
    private String trip_lat;
    private String trip_lng;
    private String trip_address;
//    private String trip_day;
    private LocalDateTime trip_day;
    private String trip_imageUrl;
}
