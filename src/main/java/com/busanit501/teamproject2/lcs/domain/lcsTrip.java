package com.busanit501.teamproject2.lcs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lcstrip")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class lcsTrip extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trip_id;

    @Column(nullable = false)
    private String trip_name;

    @Column(nullable = false)
    private String trip_description;

    @Column(nullable = false)
    private String trip_location;

    @Column(nullable = false)
    private String trip_rating;

    @Column(nullable = false)
    private String trip_category;

    @Column(nullable = false)
    private boolean trip_recommended;

    @Column(nullable = false)
    private String trip_imageUrl;
}
