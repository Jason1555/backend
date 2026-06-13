package com.dubinchin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club")
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
    private String vklink;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User owner;

    public Club() {
    }

    public Club(String id, String name, String logo, String description, String phone, String email, String website,
            String vklink, User owner) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.vklink = vklink;
        this.owner = owner;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVklink() {
        return vklink;
    }

    public void setVklink(String vklink) {
        this.vklink = vklink;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }    
}
