package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dubinchin.dto.CreateFestivalRequest;
import com.dubinchin.dto.FestivalDto;
import com.dubinchin.dto.UpdateFestivalRequest;
import com.dubinchin.entity.Festival;
import com.dubinchin.entity.Organizer;
import com.dubinchin.entity.User;
import com.dubinchin.entity.enums.FestivalStatus;
import com.dubinchin.entity.enums.UserRole;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.exception.UnauthorizedAccessException;
import com.dubinchin.exception.ValidationException;
import com.dubinchin.mapper.FestivalMapper;
import com.dubinchin.repository.FestivalRepository;
import com.dubinchin.repository.OrganizerRepository;
import com.dubinchin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FestivalService {
    private final FestivalRepository festivalRepository;
    private final OrganizerRepository organizerRepository;
    private final UserRepository userRepository;
    private final FestivalMapper festivalMapper;

    @Transactional(readOnly = true)
    public List<FestivalDto> getFestivals(String epoch, FestivalStatus status) {
        List<Festival> festivals;
        if (epoch != null && status != null) {
            festivals = festivalRepository.findByEpochAndStatus(epoch, status);
        } else if (epoch != null) {
            festivals = festivalRepository.findByEpoch(epoch);
        } else if (status != null) {
            festivals = festivalRepository.findByStatus(status);
        } else {
            festivals = festivalRepository.findAll();
        }
        
        return festivals.stream().map(festivalMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public FestivalDto getFestivalById(String festivalId) {
        Festival festival = getFestivalOrThrow(festivalId);
        return festivalMapper.toDto(festival);
    }

    public FestivalDto createFestival(String userId, CreateFestivalRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found"));

        if (user.getRole() != UserRole.ORGANIZER) {
            throw new UnauthorizedAccessException("Only users with ORGANIZER role can create festivals. User ID: " + userId);
        }

        Organizer organizer = organizerRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Organizer with ID: " + userId + " not found"));

        Festival festival = Festival.builder()
            .name(request.getName())
            .epoch(request.getEpoch())
            .date(LocalDate.parse(request.getDate()))
            .city(request.getCity())
            .location(request.getLocation())
            .requirementsFileUrl(request.getRequirementsFileUrl())
            .organizer(organizer)
            .createdAt(LocalDate.now())
            .status(FestivalStatus.PLANNED)
            .build();

        Festival saved = festivalRepository.save(festival);
        return festivalMapper.toDto(saved);
    }

    public FestivalDto updateFestival(String festivalId, UpdateFestivalRequest request) {
        Festival festival = getFestivalOrThrow(festivalId);
        festivalMapper.updateEntity(festival, request);
        
        Festival updated = festivalRepository.save(festival);
        return festivalMapper.toDto(updated);
    }

    public FestivalDto changeStatus(String festivalId, FestivalStatus newStatus) {
        Festival festival = getFestivalOrThrow(festivalId);
        validateStatusTransition(festival.getStatus(), newStatus);
        
        festival.setStatus(newStatus);
        Festival updated = festivalRepository.save(festival);
        
        return festivalMapper.toDto(updated);
    }

    public void deleteFestival(String festivalId) {
        Festival festival = getFestivalOrThrow(festivalId);
        
        if (festival.getStatus() == FestivalStatus.CANCELLED || 
            festival.getStatus() == FestivalStatus.COMPLETED) {
            throw new ValidationException("Cannot delete festival with status: " + festival.getStatus());
        }

        festivalRepository.delete(festival);
    }

    private Festival getFestivalOrThrow(String festivalId) {
        return festivalRepository.findById(festivalId)
            .orElseThrow(() -> new ResourceNotFoundException("Festival with ID: " + festivalId + " not found"));
    }

    private void validateStatusTransition(FestivalStatus current, FestivalStatus target) {
        boolean valid = switch (current) {
            case PLANNED -> target == FestivalStatus.ONGOING || target == FestivalStatus.CANCELLED;
            case ONGOING -> target == FestivalStatus.COMPLETED || target == FestivalStatus.CANCELLED;
            case COMPLETED, CANCELLED -> false;
        };

        if (!valid) {
            throw new ValidationException("Invalid status transition from " + current + " to " + target);
        }
    }
}
