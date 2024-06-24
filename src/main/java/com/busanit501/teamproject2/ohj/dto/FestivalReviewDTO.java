package com.busanit501.teamproject2.ohj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter

public class FestivalReviewDTO {
    private Long festivalReviewId;
    private Long festivalDestinationId;
    private String festivalReviewUserName;
    private Long festivalRating;
    private String festivalComment;

    // Getters and setters
}
