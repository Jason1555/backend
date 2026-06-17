package com.dubinchin.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.dubinchin.controller.ClubController;
import com.dubinchin.dto.ClubDto;
import com.dubinchin.dto.UpdateClubRequest;
import com.dubinchin.service.ClubService;

@RestController
@RequiredArgsConstructor
public class ClubControllerImpl implements ClubController {
    private final ClubService clubService;

    @Override
    public ClubDto getClub(String id) {
        return clubService.getClubById(id);
    }

    @Override
    public ClubDto updateClub(String id, UpdateClubRequest request) {
        return clubService.updateClub(id, request);
    }
}
