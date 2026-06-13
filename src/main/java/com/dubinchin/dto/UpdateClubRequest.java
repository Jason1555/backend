package com.dubinchin.dto;

import lombok.Data;

@Data
public class UpdateClubRequest {
    private String name;
    private String logo;
    private String description;
    private String phone;
    private String email;
    private String website;
    private String vkLink;
}
