package com.dubinchin.entity;

import java.time.LocalDate;

import com.dubinchin.entity.enums.FestivalStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "festival")
public class Festival {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String epoch;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String location;
    private String requirementsFileUrl;
    
    @Column(nullable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FestivalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "organizer_id",
        nullable = false
    )
    private Organizer organizer;

    public Festival() {
    }

    public Festival(String id, String name, String epoch, LocalDate date, String location, String requirementsFileUrl,
            LocalDate createdAt, FestivalStatus status, Organizer organizer) {
        this.id = id;
        this.name = name;
        this.epoch = epoch;
        this.date = date;
        this.location = location;
        this.requirementsFileUrl = requirementsFileUrl;
        this.createdAt = createdAt;
        this.status = status;
        this.organizer = organizer;
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

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirementsFileUrl() {
        return requirementsFileUrl;
    }

    public void setRequirementsFileUrl(String requirementsFileUrl) {
        this.requirementsFileUrl = requirementsFileUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public FestivalStatus getStatus() {
        return status;
    }

    public void setStatus(FestivalStatus status) {
        this.status = status;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
