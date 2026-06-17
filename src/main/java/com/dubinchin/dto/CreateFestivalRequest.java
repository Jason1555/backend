package com.dubinchin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFestivalRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String epoch;

    @NotBlank
    private String date;

    @NotBlank
    private String city;
    
    @NotBlank
    private String location;

    private String requirementsFileUrl;
}
