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
    private String trip_description;
    private String trip_location;
    private String trip_rating;
    private String trip_category;
    private boolean trip_recommended;
    private String trip_imageUrl;
}
