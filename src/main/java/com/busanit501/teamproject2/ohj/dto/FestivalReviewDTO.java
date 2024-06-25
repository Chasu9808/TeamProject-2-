package com.busanit501.teamproject2.ohj.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FestivalReviewDTO {

    private Long festivalReviewId;
    private Long festivalDestinationId;
    private String festivalReviewUserName;
    private Long festivalRating;
    private String festivalComment;

    // Getters and setters
}
