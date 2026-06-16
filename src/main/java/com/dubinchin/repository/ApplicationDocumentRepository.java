package com.dubinchin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dubinchin.entity.ApplicationDocument;

public interface ApplicationDocumentRepository extends JpaRepository<ApplicationDocument, String> {
    List<ApplicationDocument> findByApplicationId(String applicationId);
}
