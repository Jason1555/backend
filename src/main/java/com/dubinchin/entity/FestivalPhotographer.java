package com.dubinchin.entity;

import com.dubinchin.entity.enums.PhotographerStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "festival_photographer")
public class FestivalPhotographer {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotographerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "festival_id",
            nullable = false
    )
    private Festival festival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "photographer_id",
            nullable = false
    )
    private Photographer photographer;

    public FestivalPhotographer() {
    }

    public FestivalPhotographer(String id, PhotographerStatus status, Festival festival, Photographer photographer) {
        this.id = id;
        this.status = status;
        this.festival = festival;
        this.photographer = photographer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PhotographerStatus getStatus() {
        return status;
    }

    public void setStatus(PhotographerStatus status) {
        this.status = status;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }
}