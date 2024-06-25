package com.busanit501.teamproject2.ohj.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "festivalreview", indexes = {
        @Index(name = "idx_festivalreview_festival_review_id", columnList = "festival_review_id")
})
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "board")
@ToString

public class FestivalReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festivalReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Festival festival;

    private String festivalReviewUserName;
    private Long festivalRating;
    private String festivalComment;


    public void chageCommnet(String text) {
        this.festivalComment = text;
    }
    // Getters and setters
}
