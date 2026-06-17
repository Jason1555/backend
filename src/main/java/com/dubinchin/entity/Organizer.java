package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "organizer")
@Data
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User user;
}
