package com.dubinchin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FestivalDto {
    private String id;
    private String name;
    private String epoch;
    private String date;
    private String city;
    private String location;
    private String requirementsFileUrl;
    private String organizerId;
    private String createdAt;
    private String status;
}
