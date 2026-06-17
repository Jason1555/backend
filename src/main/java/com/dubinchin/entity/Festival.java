package com.dubinchin.entity;

import java.time.LocalDate;

import com.dubinchin.entity.enums.FestivalStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "festival")
@Data
public class Festival {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String epoch;

    @Column(nullable = false)
    private LocalDate date;

    private String city;

    @Column(nullable = false)
    private String location;
    private String requirementsFileUrl;
    
    @Column(nullable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FestivalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "organizer_id",
        nullable = false
    )
    private Organizer organizer;
}
