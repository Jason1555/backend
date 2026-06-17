package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dubinchin.entity.Application;
import com.dubinchin.entity.enums.ApplicationStatus;


public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByFestivalId(String festivalId);
    List<Application> findByClubId(String clubId);
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByFestivalIdAndStatus(String festivalId, ApplicationStatus status);
    List<Application> findByFestivalIdAndClubId(String festivalId, String clubId);
    boolean existsByFestivalIdAndClubId(String festivalId, String clubId);

    @Query("SELECT a FROM Application a WHERE a.festival.organizer.user.id = :organizerId")
    List<Application> findByOrganizerIdAllApplications(@Param("organizerId") String organizerId);
    
    @Query("SELECT a FROM Application a WHERE a.festival.organizer.user.id = :organizerId AND a.status = :status")
    List<Application> findByOrganizerIdAndStatus(
        @Param("organizerId") String organizerId,
        @Param("status") ApplicationStatus status
    );
    
    @Query("SELECT a FROM Application a WHERE a.festival.organizer.user.id = :organizerId AND a.festival.id = :festivalId")
    List<Application> findByOrganizerIdAndFestivalId(
        @Param("organizerId") String organizerId,
        @Param("festivalId") String festivalId
    );
    
    @Query("SELECT a FROM Application a WHERE a.festival.organizer.user.id = :organizerId AND a.festival.id = :festivalId AND a.status = :status")
    List<Application> findByOrganizerIdAndFestivalIdAndStatus(
        @Param("organizerId") String organizerId,
        @Param("festivalId") String festivalId,
        @Param("status") ApplicationStatus status
    );
}
