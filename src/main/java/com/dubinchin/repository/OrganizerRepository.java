package com.dubinchin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dubinchin.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, String> {
    Optional<Organizer> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
