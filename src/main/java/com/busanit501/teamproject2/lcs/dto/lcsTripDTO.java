package com.busanit501.teamproject2.lcs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class lcsTripDTO {
    private Long trip_id;
    private String trip_name;
    private String description;
    private String location;
    private String rating;
    private String category;
    private boolean recommended;
}
