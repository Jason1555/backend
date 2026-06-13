package com.dubinchin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "photographer")
public class Photographer {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    private String portfolioUrl;

    public Photographer() {
    }

    public Photographer(String id, String name, String contactInfo, String portfolioUrl) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.portfolioUrl = portfolioUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }
}
