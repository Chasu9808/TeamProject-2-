package com.busanit501.teamproject2.ohj.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
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

    // Getters and setters
}