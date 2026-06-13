package com.dubinchin.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dubinchin.entity.enums.ApplicationStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "application")
@Data
public class Application {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    private LocalDateTime reviewedAt;
    
    @Column(columnDefinition = "TEXT")
    private String reviewerNotes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "festival_id",
        nullable = false
    )
    private Festival festival;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "club_id",
        nullable = false
    )
    private Club club;

    @OneToMany(
        mappedBy = "application",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ApplicationDocument> documents =
            new ArrayList<>();

}
