package com.dubinchin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "photographer")
@Data
public class Photographer {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    private String portfolioUrl;

}
