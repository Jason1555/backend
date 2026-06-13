package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "organizer")
@Data
public class Organizer {
    @Id
    private String id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User user;
}
