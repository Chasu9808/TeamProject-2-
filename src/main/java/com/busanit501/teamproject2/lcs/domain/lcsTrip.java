package com.busanit501.teamproject2.lcs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lcstrip")
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class lcsTrip extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trip_id;
    // 자동증가 id 구분용

    @Column(nullable = false)
    private String trip_name;
    // 여행지 명칭

    @Column(length = 2000, nullable = false)
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
