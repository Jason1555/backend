package com.dubinchin.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateApplicationRequest {
    @NotBlank
    private String festivalId;

    @NotBlank
    private String clubId;

    private List<ApplicationDocumentDto> documents;
    private String description;
}
