package com.dubinchin.entity;

import com.dubinchin.entity.enums.PhotographerStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "festival_photographer")
@Data
public class FestivalPhotographer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotographerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "festival_id",
            nullable = false
    )
    private Festival festival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "photographer_id",
            nullable = false
    )
    private Photographer photographer;
}
