package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "photographer")
@Data
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    private String portfolioUrl;

}
