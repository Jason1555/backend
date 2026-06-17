package com.dubinchin.controller.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.dubinchin.controller.FestivalController;
import com.dubinchin.dto.CreateFestivalRequest;
import com.dubinchin.dto.FestivalDto;
import com.dubinchin.dto.UpdateFestivalRequest;
import com.dubinchin.entity.enums.FestivalStatus;
import com.dubinchin.service.FestivalService;

@RestController
@RequiredArgsConstructor
public class FestivalControllerImpl implements FestivalController {
    private final FestivalService festivalService;

    @Override
    public List<FestivalDto> getFestivals(String epoch, FestivalStatus status) {
        return festivalService.getFestivals(epoch, status);
    }

    @Override
    public FestivalDto getFestival(String id) {
        return festivalService.getFestivalById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public FestivalDto createFestival(String userId, CreateFestivalRequest request) {
        return festivalService.createFestival(userId, request);
    }

    @Override
    public FestivalDto updateFestival(String id, UpdateFestivalRequest request) {
        return festivalService.updateFestival(id, request);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFestival(String id) {
        festivalService.deleteFestival(id);
    }
}
