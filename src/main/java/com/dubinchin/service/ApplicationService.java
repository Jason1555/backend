package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.ApplicationDto;
import com.dubinchin.dto.CreateApplicationRequest;
import com.dubinchin.dto.UpdateApplicationStatusRequest;
import com.dubinchin.entity.Application;
import com.dubinchin.entity.Club;
import com.dubinchin.entity.Festival;
import com.dubinchin.entity.User;
import com.dubinchin.entity.enums.ApplicationStatus;
import com.dubinchin.entity.enums.FestivalStatus;
import com.dubinchin.entity.enums.UserRole;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.exception.UnauthorizedAccessException;
import com.dubinchin.exception.ValidationException;
import com.dubinchin.mapper.ApplicationMapper;
import com.dubinchin.repository.ApplicationRepository;
import com.dubinchin.repository.ClubRepository;
import com.dubinchin.repository.FestivalRepository;
import com.dubinchin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ClubRepository clubRepository;
    private final FestivalRepository festivalRepository;
    private final UserRepository userRepository;
    private final ApplicationMapper applicationMapper;

    @Transactional(readOnly = true)
    public List<ApplicationDto> getApplications(
            String festivalId,
            String clubId,
            ApplicationStatus status
    ) {
        if (festivalId != null) {
            return applicationRepository.findByFestivalId(festivalId)
                    .stream()
                    .map(applicationMapper::toDto)
                    .toList();
        }
        if (clubId != null) {
            return applicationRepository.findByClubId(clubId)
                    .stream()
                    .map(applicationMapper::toDto)
                    .toList();
        }
        if (status != null) {
            return applicationRepository.findByStatus(status)
                    .stream()
                    .map(applicationMapper::toDto)
                    .toList();
        }
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ApplicationDto getApplicationById(String applicationId) {
        Application application = getApplicationOrThrow(applicationId);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto createApplication(
            String userId,
            CreateApplicationRequest request
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User with ID: " + userId + " not found"));
        if (user.getRole() != UserRole.CLUB) {
            throw new UnauthorizedAccessException(
                    "Only users with CLUB role can submit applications. User ID: " + userId);
        }
        Club club = clubRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Club for user ID: " + userId + " not found"));
        Festival festival = festivalRepository
                .findById(request.getFestivalId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Festival with ID: " + request.getFestivalId() + " not found"));
        if (festival.getStatus() != FestivalStatus.PLANNED) {
            throw new ValidationException(
                    "Applications for festival with ID " + festival.getId() + " are closed. Current status: " + festival.getStatus());
        }
        boolean alreadyExists =
                applicationRepository.existsByFestivalIdAndClubId(
                        festival.getId(),
                        club.getId()
                );
        if (alreadyExists) {
            throw new ValidationException(
                    "Application already submitted for festival ID " + festival.getId() + " and club ID " + club.getId());
        }
        Application application = new Application();
        application.setFestival(festival);
        application.setClub(club);
        application.setStatus(ApplicationStatus.PENDING);
        application.setDescription(
                request.getDescription()
        );
        application.setSubmittedAt(
                LocalDateTime.now()
        );
        Application saved =
                applicationRepository.save(application);
        return applicationMapper.toDto(saved);
    }

    public ApplicationDto updateStatus(
            String applicationId,
            UpdateApplicationStatusRequest request
    ) {
        Application application =
                getApplicationOrThrow(applicationId);
        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new ValidationException(
                    "Application with ID " + applicationId + " has already been reviewed. Current status: " + application.getStatus());
        }
        application.setStatus(ApplicationStatus.valueOf(request.getStatus()));
        application.setReviewerNotes(
                request.getReviewerNotes()
        );
        application.setReviewedAt(
                LocalDateTime.now()
        );
        Application saved =
                applicationRepository.save(application);
        return applicationMapper.toDto(saved);
    }

    private Application getApplicationOrThrow(
            String applicationId
    ) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application with ID: " + applicationId + " not found"));
    }
}
