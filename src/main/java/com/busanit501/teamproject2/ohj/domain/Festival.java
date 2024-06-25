package com.busanit501.teamproject2.ohj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 이클래스는 실제 데이터베이스에 영향이 있다.
// 반드시, pk 있어야 함.
@Entity
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festivalId;

    private String festivalName;
    private String festivalLocation;
    private String festivalDescription;
    private String imageUrl;
    private LocalDate festivalStart;
    private LocalDate festivalEnd;

    @OneToMany(mappedBy = "festival",
            // 부모 테이블의 1차 캐시 테이블에 작업시, 하위 테이블에도 다 적용함.
            cascade = {CascadeType.ALL},
            // 데이터베이스 조금 늦게 반영하겠다.
            // 기본값은 FetchType.EAGER 즉시로딩인데 변경함
            fetch = FetchType.LAZY,
            // 데이터베이스에서, 부모 보드 번호 null, 삭제 하기.
            orphanRemoval = true)
    @Builder.Default
    // N + 1, 부모 테이블을 조회시, 각 자식테이블(첨부이미지 테이블), 각각 하나씩 조회를 함.
    // 매번 디비 연결하는 자원 소모가 발생을함.
    // 해결책 . @BatchSize( size = 20), 지정된 갯수만큼 모아서, 한번에 처리함.
    @BatchSize(size = 20)
    private Set<FestivalImage> imageSet = new HashSet<>();


    public void addImage(String uuid, String fileName) {
        FestivalImage festivalImage = FestivalImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .festival(this)
                .ord(imageSet.size())
                .build();

        imageSet.add(festivalImage);
    }


    // Getters and setters
}