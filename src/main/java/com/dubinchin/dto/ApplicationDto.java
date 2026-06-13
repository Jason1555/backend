package com.dubinchin.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {

    private String id;

    private String festivalId;

    private String clubId;

    private String status;

    private List<ApplicationDocumentDto> documents;

    private String description;

    private String submittedAt;

    private String reviewedAt;

    private String reviewerNotes;
}
