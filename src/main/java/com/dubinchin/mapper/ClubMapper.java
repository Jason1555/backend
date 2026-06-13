package com.dubinchin.mapper;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.ClubDto;
import com.dubinchin.dto.UpdateClubRequest;
import com.dubinchin.entity.Club;

@Component
public class ClubMapper {
    public ClubDto toDto(Club club) {
        if (club == null) {
            return null;
        }
        
        return ClubDto.builder()
            .id(club.getId())
            .name(club.getName())
            .logo(club.getLogo())
            .description(club.getDescription())
            .phone(club.getPhone())
            .email(club.getEmail())
            .website(club.getWebsite())
            .vkLink(club.getVklink())
            .userId(club.getOwner().getId())
            .build();
    }

    public void updateEntity(Club club, UpdateClubRequest request) {
        if (request.getName() != null) {
            club.setName(request.getName());
        }
        
        if (request.getLogo() != null) {
            club.setLogo(request.getLogo());
        }
        
        if (request.getDescription() != null) {
            club.setDescription(request.getDescription());
        }
        
        if (request.getPhone() != null) {
            club.setPhone(request.getPhone());
        }
        
        if (request.getEmail() != null) {
            club.setEmail(request.getEmail());
        }
        
        if (request.getWebsite() != null) {
            club.setWebsite(request.getWebsite());
        }

        if (request.getVkLink() != null) {
            club.setVklink(request.getVkLink());
        }
    }
}
