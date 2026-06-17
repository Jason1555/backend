package com.dubinchin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubDto {
    private String id;
    private String name;
    private String logo;
    private String description;
    private String phone;
    private String email;
    private String website;
    private String vkLink;
    private String userId;
}
