package com.dubinchin.controller.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.dubinchin.controller.ApplicationController;
import com.dubinchin.dto.ApplicationDto;
import com.dubinchin.dto.CreateApplicationRequest;
import com.dubinchin.dto.UpdateApplicationStatusRequest;
import com.dubinchin.entity.enums.ApplicationStatus;
import com.dubinchin.service.ApplicationService;

@RestController
@RequiredArgsConstructor
public class ApplicationControllerImpl implements ApplicationController {
    private final ApplicationService applicationService;

    @Override
    public List<ApplicationDto> getApplications(String festivalId, String clubId, ApplicationStatus status) {
        return applicationService.getApplications(festivalId, clubId, status);
    }

    @Override
    public ApplicationDto getApplication(String id) {
        return applicationService.getApplicationById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDto createApplication(String userId, CreateApplicationRequest request) {
        return applicationService.createApplication(userId, request);
    }

    @Override
    public ApplicationDto updateStatus(String id, UpdateApplicationStatusRequest request) {
        return applicationService.updateStatus(id, request);
    }
}
