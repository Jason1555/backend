package com.dubinchin.dto;

import lombok.Data;

@Data
public class UpdateFestivalRequest {
    private String name;
    private String epoch;
    private String date;
    private String location;
    private String requirementsFileUrl;
    private String status;
}
