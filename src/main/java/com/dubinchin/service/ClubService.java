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

    @Transactional(readOnly = true)
    public ClubDto getClubById(String clubId) {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new ResourceNotFoundException("Club with ID: " + clubId + " not found"));

        return clubMapper.toDto(club);
    }

    public ClubDto updateClub(String clubId, UpdateClubRequest request) {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new ResourceNotFoundException("Club with ID: " + clubId + " not found"));

        clubMapper.updateEntity(club, request);
        Club updated = clubRepository.save(club);
        
        return clubMapper.toDto(updated);
    }
}
