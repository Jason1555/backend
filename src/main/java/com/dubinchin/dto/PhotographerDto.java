package com.dubinchin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotographerDto {
    private String id;
    private String name;
    private String contactInfo;
    private String portfolioUrl;
}
