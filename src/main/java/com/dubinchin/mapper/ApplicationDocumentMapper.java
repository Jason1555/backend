package com.dubinchin.mapper;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.ApplicationDocumentDto;
import com.dubinchin.entity.ApplicationDocument;

@Component
public class ApplicationDocumentMapper {
    public ApplicationDocumentDto toDto(ApplicationDocument document) {
        if (document == null) {
            return null;
        }

        return ApplicationDocumentDto.builder()
            .id(document.getId())
            .name(document.getName())
            .type(document.getType().name().toLowerCase())
            .url(document.getUrl())
            .uploadedAt(document.getUploadedAt().toString())
            .build();
    }
}
