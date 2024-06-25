package com.busanit501.teamproject2.hjt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "hjttripreply", indexes = {
        @Index(name = "idx_hjt_reply_board_trip_bno", columnList = "HjtTripBoard_tripBno")
})
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HjtTripReply extends HjtBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripRno;
    @ManyToOne(fetch = FetchType.LAZY)
    private HjtTripBoard hjtTripBoard;
    private String replyText;
    private String replyer;
}
