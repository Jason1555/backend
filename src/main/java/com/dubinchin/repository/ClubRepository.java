package com.dubinchin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dubinchin.entity.Club;

public interface ClubRepository extends JpaRepository<Club, String>{
    @Query("SELECT c FROM Club c WHERE c.owner.id = :userId")
    Optional<Club> findByUserId(String userId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Club c WHERE c.owner.id = :userId")
    boolean existsByUserId(String userId);
}
