package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateApplicationStatusRequest {
    @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Invalid status")
    @NotBlank(message = "Status cannot be blank")
    private String status;
    
    private String reviewerNotes;
}
