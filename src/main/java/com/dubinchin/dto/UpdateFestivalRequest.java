package com.dubinchin.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFestivalRequest {
    private String name;
    private String epoch;
    
    private String date;
    
    private String city;
    private String location;
    private String requirementsFileUrl;
    
    @Pattern(regexp = "PLANNED|ONGOING|COMPLETED|CANCELLED", message = "Invalid status")
    private String status;
}