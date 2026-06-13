package com.dubinchin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.Club;

public interface ClubRepository extends JpaRepository<Club, String>{
    Optional<Club> findByUserId(String userId);
    boolean exiexistsByUserId(String userId);
}
