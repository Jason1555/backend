package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "club")
@Data
public class Club {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;
    private String logo;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String phone;

    @Column(nullable = false)
    private String email;
    private String website;
    private String vkLink;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User owner;
}
