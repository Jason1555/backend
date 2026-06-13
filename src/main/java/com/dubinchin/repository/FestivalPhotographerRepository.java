package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.FestivalPhotographer;

public interface FestivalPhotographerRepository extends JpaRepository<FestivalPhotographer, String>{
    List<FestivalPhotographer> findByFestivalId(String festivalId);
    List<FestivalPhotographer> findByPhotographerId(String photographerId);
}
