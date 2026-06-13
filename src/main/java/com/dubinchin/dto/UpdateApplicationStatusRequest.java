package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateApplicationStatusRequest {
    @NotBlank
    private String status;
    private String reviewerNotes;
}
