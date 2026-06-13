package com.dubinchin.dto;

import lombok.*;

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
