package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.ClubDto;
import com.dubinchin.dto.UpdateClubRequest;
import com.dubinchin.entity.Club;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.mapper.ClubMapper;
import com.dubinchin.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

        public ClubDto getClubById(String clubId) {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new RuntimeException("Club " + clubId + " not found"));

        return clubMapper.toDto(club);
    }

    public ClubDto updateClub(String clubId, UpdateClubRequest request) {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new ResourceNotFoundException("Club with id " + clubId + " not found"));
        applyUpdates(club, request);
        return clubMapper.toDto(clubRepository.save(club));
    }

    private void applyUpdates(Club club, UpdateClubRequest request) {
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
            club.setVkLink(request.getVkLink());
        }
    }
}
