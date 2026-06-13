package com.dubinchin.entity;

import com.dubinchin.entity.enums.UserRole;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public User() {
    }

    public User(String id, String email, String name, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
