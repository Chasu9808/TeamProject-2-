package com.busanit501.teamproject2.lcs.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lcstrip")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class lcsTrip extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trip_id;
    // 자동증가 id 구분용

    @Column(nullable = false)
    private String trip_name;
    // 여행지 명칭

    @Lob // MEDIUMTEXT에 해당하는 @Lob 어노테이션 추가
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String trip_description;
    // 여행지 설명

    @Column(nullable = false)
    private String trip_lat;
    // 여행지 위도

    @Column(nullable = false)
    private String trip_lng;
    // 여행지 경도

    @Column(nullable = false)
    private String trip_address;
    // 여행지 지역

    @Column(nullable = false)
    private String trip_day;
    // 여행지 운영날짜

    @Column(nullable = false)
    private String trip_imageUrl;
    // 여행지 이미지 링크
}
