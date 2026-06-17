package com.dubinchin.entity;

import com.dubinchin.entity.enums.PhotographerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "festival_photographer", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"festival_id", "photographer_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FestivalPhotographer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotographerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photographer_id", nullable = false)
    private Photographer photographer;
}