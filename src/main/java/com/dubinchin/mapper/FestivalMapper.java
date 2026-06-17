package com.dubinchin.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.CreateFestivalRequest;
import com.dubinchin.dto.FestivalDto;
import com.dubinchin.dto.UpdateFestivalRequest;
import com.dubinchin.entity.Festival;
import com.dubinchin.entity.enums.FestivalStatus;

@Component
public class FestivalMapper {
    public FestivalDto toDto(Festival festival) {
        if (festival == null) {
            return null;
        }
        
        return FestivalDto.builder()
            .id(festival.getId())
            .name(festival.getName())
            .epoch(festival.getEpoch())
            .date(festival.getDate().toString())
            .city(festival.getCity())
            .location(festival.getLocation())
            .requirementsFileUrl(festival.getRequirementsFileUrl())
            .organizerId(festival.getOrganizer().getId())
            .createdAt(festival.getCreatedAt().toString())
            .status(festival.getStatus().name().toString())
            .build();
    }

    public Festival toEntity(CreateFestivalRequest request) {
        Festival festival = new Festival();
        festival.setName(request.getName());
        festival.setEpoch(request.getEpoch());
        festival.setDate(LocalDate.parse(request.getDate()));
        festival.setCity(request.getCity());
        festival.setLocation(request.getLocation());
        festival.setRequirementsFileUrl(request.getRequirementsFileUrl());
        return festival;
    }

    public void updateEntity(Festival festival, UpdateFestivalRequest request) {
        if (request.getName() != null) {
            festival.setName(request.getName());
        }
        
        if (request.getEpoch() != null) {
            festival.setEpoch(request.getEpoch());
        }
        
        if (request.getDate() != null) {
            festival.setDate(LocalDate.parse(request.getDate()));
        }
        
        if (request.getLocation() != null) {
            festival.setLocation(request.getLocation());
        }
        
        if (request.getRequirementsFileUrl() != null) {
            festival.setRequirementsFileUrl(request.getRequirementsFileUrl());
        }

        if (request.getStatus() != null) {
            festival.setStatus(FestivalStatus.valueOf(request.getStatus()));
        }
    } 
}
