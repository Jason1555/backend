package com.dubinchin.controller.impl;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.dubinchin.controller.IntegrationController;
import com.dubinchin.dto.PhotographerDto;
import com.dubinchin.service.PhotographerIntegrationService;

@RestController
@RequiredArgsConstructor
public class IntegrationControllerImpl implements IntegrationController {
    private final PhotographerIntegrationService integrationService;

    @Override
    public List<PhotographerDto> getPhotographers(String festivalId) {
        return integrationService.getFestivalPhotographers(festivalId);
    }

    @Override
    public void hirePhotographer(String festivalId, String photographerId) {
        integrationService.hirePhotographer(festivalId, photographerId);
    }
}
