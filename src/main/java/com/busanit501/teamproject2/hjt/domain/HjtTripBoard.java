package com.busanit501.teamproject2.hjt.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "hjtTripBoard", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
    orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName) {
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .hjtTripBoard(this)
                .ord(imageSet.size())
                .build();

        imageSet.add(boardImage);
    }

    // 이미지들 삭제
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }

    public void changeTripTitleAndContent(String tripTitle, String tripContent) {
        this.tripTitle = tripTitle;
        this.tripContent = tripContent;
    }
}
