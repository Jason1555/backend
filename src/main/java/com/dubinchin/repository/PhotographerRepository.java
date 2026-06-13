package com.dubinchin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dubinchin.entity.Photographer;

public interface PhotographerRepository extends JpaRepository<Photographer, String>{
    
}
