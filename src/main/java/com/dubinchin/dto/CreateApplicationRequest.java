package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateApplicationRequest {
    @NotBlank(message = "Festival ID cannot be blank")
    private String festivalId;

    @NotBlank(message = "Club ID cannot be blank")
    private String clubId;

    private String description;
}
