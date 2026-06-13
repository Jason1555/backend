package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.Festival;
import com.dubinchin.entity.enums.FestivalStatus;


public interface FestivalRepository extends JpaRepository<Festival, String>{
    List<Festival> findByStatus(FestivalStatus status);
    List<Festival> findByOrganizerId(String organizerId);
    List<Festival> findByNameContainingIgnoreCase(String name);
}
