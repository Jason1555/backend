package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateApplicationDocumentRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String type; 

    @NotBlank
    private String url;
}
