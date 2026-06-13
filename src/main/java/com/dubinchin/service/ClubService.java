package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.ClubDto;
import com.dubinchin.entity.Club;
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
}
