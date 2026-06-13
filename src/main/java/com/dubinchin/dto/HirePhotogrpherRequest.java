package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HirePhotogrpherRequest {
    @NotBlank
    private String photographerId;
}
