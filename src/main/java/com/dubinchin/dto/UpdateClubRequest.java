package com.dubinchin.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateClubRequest {
    private String name;
    private String logo;
    private String description;
    private String phone;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private String website;
    private String vkLink;
}
