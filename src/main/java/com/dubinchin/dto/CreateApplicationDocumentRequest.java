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
public class CreateApplicationDocumentRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    
    @Pattern(regexp = "DOCUMENT|PHOTO|TEXT", message = "Type must be DOCUMENT, PHOTO or TEXT")
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "URL cannot be blank")
    private String url;
}
