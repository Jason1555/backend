package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.Application;
import com.dubinchin.entity.enums.ApplicationStatus;


public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByFestivalId(String festivalId);
    List<Application> findByClubId(String clubId);
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByFestivalIdAndStatus(String festivalId, ApplicationStatus status);
    List<Application> findByFestivalIdAndClubId(String festivalId, String clubId);
    boolean existsByFestivalIdAndClubId(String festivalId, String clubId);
}
