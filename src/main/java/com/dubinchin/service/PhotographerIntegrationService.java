package com.dubinchin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.PhotographerDto;
import com.dubinchin.entity.Festival;
import com.dubinchin.entity.FestivalPhotographer;
import com.dubinchin.entity.Photographer;
import com.dubinchin.entity.enums.PhotographerStatus;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.repository.FestivalPhotographerRepository;
import com.dubinchin.repository.FestivalRepository;
import com.dubinchin.repository.PhotographerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotographerIntegrationService {

    private final FestivalRepository festivalRepository;
    private final PhotographerRepository photographerRepository;
    private final FestivalPhotographerRepository festivalPhotographerRepository;

    @Transactional(readOnly = true)
    public List<PhotographerDto> getFestivalPhotographers(String festivalId) {
        return festivalPhotographerRepository
                .findByFestivalId(festivalId)
                .stream()
                .map(FestivalPhotographer::getPhotographer)
                .map(this::toDto)
                .toList();
    }

    public void hirePhotographer(String festivalId, String photographerId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        Photographer photographer = photographerRepository.findById(photographerId)
                .orElseThrow(() -> new ResourceNotFoundException("Photographer not found"));
        FestivalPhotographer relation = new FestivalPhotographer();
        relation.setFestival(festival);
        relation.setPhotographer(photographer);
        relation.setStatus(PhotographerStatus.HIRED);
        festivalPhotographerRepository.save(relation);
    }

    private PhotographerDto toDto(Photographer photographer) {
        return PhotographerDto.builder()
                .id(photographer.getId())
                .name(photographer.getName())
                .contactInfo(photographer.getContactInfo())
                .portfolioUrl(photographer.getPortfolioUrl())
                .build();
    }
}
