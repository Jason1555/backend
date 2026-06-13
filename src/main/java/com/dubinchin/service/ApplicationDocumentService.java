package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.ApplicationDocumentDto;
import com.dubinchin.dto.CreateApplicationDocumentRequest;
import com.dubinchin.entity.Application;
import com.dubinchin.entity.ApplicationDocument;
import com.dubinchin.entity.enums.DocumentType;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.mapper.ApplicationDocumentMapper;
import com.dubinchin.repository.ApplicationDocumentRepository;
import com.dubinchin.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationDocumentService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationDocumentRepository documentRepository;
    private final ApplicationDocumentMapper documentMapper;

    @Transactional(readOnly = true)
    public List<ApplicationDocumentDto> getDocuments(
            String applicationId
    ) {
        return documentRepository
                .findByApplicationId(applicationId)
                .stream()
                .map(documentMapper::toDto)
                .toList();
    }

    public ApplicationDocumentDto addDocument(
            String applicationId,
            CreateApplicationDocumentRequest request
    ) {
        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found with ID: " + applicationId));
        ApplicationDocument document =
                new ApplicationDocument();
        document.setApplication(application);
        document.setName(request.getName());
        document.setType(DocumentType.valueOf(request.getType()));
        document.setUrl(request.getUrl());
        document.setUploadedAt(LocalDateTime.now());
        return documentMapper.toDto(
                documentRepository.save(document)
        );
    }

    public void deleteDocument(
            String documentId
    ) {
        if (!documentRepository.existsById(documentId)) {
            throw new ResourceNotFoundException(
                    "Document not found with ID: " + documentId);
        }
        documentRepository.deleteById(documentId);
    }
}
