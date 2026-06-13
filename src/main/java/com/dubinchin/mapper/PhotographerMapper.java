package com.dubinchin.mapper;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.PhotographerDto;
import com.dubinchin.entity.Photographer;

@Component
public class PhotographerMapper {

    public PhotographerDto toDto(Photographer photographer) {
        if (photographer == null) {
            return null;
        }

        return PhotographerDto.builder()
            .id(photographer.getId())
            .name(photographer.getName())
            .contactInfo(photographer.getContactInfo())
            .portfolioUrl(photographer.getPortfolioUrl())
            .build();
    }
}
