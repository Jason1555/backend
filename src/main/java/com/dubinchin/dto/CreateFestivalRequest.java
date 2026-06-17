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
public class CreateFestivalRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Epoch cannot be blank")
    private String epoch;

    @NotBlank(message = "Date cannot be blank")
    private String date;

    @NotBlank(message = "City cannot be blank")
    private String city;
    
    @NotBlank(message = "Location cannot be blank")
    private String location;

    private String requirementsFileUrl;
}
