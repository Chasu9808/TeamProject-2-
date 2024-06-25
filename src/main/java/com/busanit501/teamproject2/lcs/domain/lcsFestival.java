package com.busanit501.teamproject2.lcs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lcsfestival")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class lcsFestival extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festival_id;
    // 자동증가 id 구분용

    @Column(nullable = false)
    private String festival_name;
    // 여행지 명칭

    @Lob // MEDIUMTEXT에 해당하는 @Lob 어노테이션 추가
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String festival_description;
    // 여행지 설명

    @Column(nullable = false)
    private String festival_lat;
    // 여행지 위도

    @Column(nullable = false)
    private String festival_lng;
    // 여행지 경도

    @Column(nullable = false)
    private String festival_address;
    // 여행지 지역

    @Column(nullable = false)
    private String festival_day;
    // 여행지 운영날짜

    @Column(nullable = false)
    private String festival_imageUrl;
    // 여행지 이미지 링크
}
