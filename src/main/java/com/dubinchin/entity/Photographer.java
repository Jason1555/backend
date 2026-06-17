package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photographer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photographer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    private String portfolioUrl;
}