package com.busanit501.teamproject2.hjt.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Table(name = "hjttripboard")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class HjtTripBoard extends HjtBaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tripBno;
    @Column(length = 500,nullable = false)
    private String tripTitle;
    @Column(length = 2000, nullable = false)
    private String tripContent;
    @Column(length = 50, nullable = false)
    private String tripWriter;

    public void changeTripTitleAndContent(String tripTitle, String tripContent) {
        this.tripTitle = tripTitle;
        this.tripContent = tripContent;
    }
}
