package com.dubinchin.mapper;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.ApplicationDto;
import com.dubinchin.entity.Application;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    private final ApplicationDocumentMapper documentMapper;

    public ApplicationDto toDto(Application application) {

        if (application == null) {
            return null;
        }

        return ApplicationDto.builder()
                .id(application.getId())
                .festivalId(
                        application.getFestival().getId()
                )
                .clubId(
                        application.getClub().getId()
                )
                .status(
                        application.getStatus()
                                .name()
                                .toLowerCase()
                )
                .description(
                        application.getDescription()
                )
                .submittedAt(
                        application.getSubmittedAt()
                                .toString()
                )
                .reviewedAt(
                        application.getReviewedAt() != null
                                ? application.getReviewedAt()
                                .toString()
                                : null
                )
                .reviewerNotes(
                        application.getReviewerNotes()
                )
                .documents(
                        application.getDocuments()
                                .stream()
                                .map(documentMapper::toDto)
                                .toList()
                )
                .build();
    }
}