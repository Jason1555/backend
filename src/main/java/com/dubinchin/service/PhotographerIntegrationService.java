package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dubinchin.dto.PhotographerDto;
import com.dubinchin.entity.Festival;
import com.dubinchin.entity.FestivalPhotographer;
import com.dubinchin.entity.Photographer;
import com.dubinchin.entity.enums.PhotographerStatus;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.exception.ValidationException;
import com.dubinchin.mapper.PhotographerMapper;
import com.dubinchin.repository.FestivalPhotographerRepository;
import com.dubinchin.repository.FestivalRepository;
import com.dubinchin.repository.PhotographerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotographerIntegrationService {
    private final FestivalRepository festivalRepository;
    private final PhotographerRepository photographerRepository;
    private final FestivalPhotographerRepository festivalPhotographerRepository;
    private final PhotographerMapper photographerMapper;

    @Transactional(readOnly = true)
    public List<PhotographerDto> getFestivalPhotographers(String festivalId) {
        if (!festivalRepository.existsById(festivalId)) {
            throw new ResourceNotFoundException("Festival not found with ID: " + festivalId);
        }

        List<PhotographerDto> photographers = festivalPhotographerRepository
            .findByFestivalId(festivalId)
            .stream()
            .map(fp -> photographerMapper.toDto(fp.getPhotographer()))
            .toList();

        return photographers;
    }

    public void hirePhotographer(String festivalId, String photographerId) {
        Festival festival = festivalRepository.findById(festivalId)
            .orElseThrow(() -> new ResourceNotFoundException("Festival not found with ID: " + festivalId));

        Photographer photographer = photographerRepository.findById(photographerId)
            .orElseThrow(() -> new ResourceNotFoundException("Photographer not found with ID: " + photographerId));

        boolean alreadyHired = festivalPhotographerRepository.findByFestivalId(festivalId)
            .stream()
            .anyMatch(fp -> fp.getPhotographer().getId().equals(photographerId));

        if (alreadyHired) {
            throw new ValidationException("Photographer already hired for this festival");
        }

        FestivalPhotographer relation = FestivalPhotographer.builder()
            .festival(festival)
            .photographer(photographer)
            .status(PhotographerStatus.HIRED)
            .build();

        festivalPhotographerRepository.save(relation);
    }
}
