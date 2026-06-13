package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.Application;
import com.dubinchin.entity.enums.ApplicationStatus;


public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByFestivalId(String festivalId);
    List<Application> findByClubId(String id);
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByFestivalAndStatus(String festivalId, ApplicationStatus status);
    List<Application> findByFestivalAndClub(String festivalId, String clubId);
}
