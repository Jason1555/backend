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
            String userId,
            String festivalId,
            String clubId,
            ApplicationStatus status) {
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found"));

        List<Application> applications;

        if (user.getRole() == UserRole.ORGANIZER) {
            applications = getOrganizerApplications(userId, festivalId, clubId, status);
        } else if (user.getRole() == UserRole.CLUB) {
            applications = getClubApplications(userId, clubId, status);
        } else {
            throw new UnauthorizedAccessException("Unknown user role: " + user.getRole());
        }

        return applications.stream().map(applicationMapper::toDto).toList();
    }

    private List<Application> getOrganizerApplications(
            String organizerId,
            String festivalId,
            String clubId,
            ApplicationStatus status) {
        
        List<Application> applications;

        if (festivalId != null) {
            // Проверяем, что фестиваль принадлежит этому организатору
            Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new ResourceNotFoundException("Festival with ID: " + festivalId + " not found"));
            
            if (!festival.getOrganizer().getUser().getId().equals(organizerId)) {
                throw new UnauthorizedAccessException(
                    "You don't have access to applications for festival ID: " + festivalId);
            }

            if (status != null) {
                applications = applicationRepository.findByFestivalIdAndStatus(festivalId, status);
            } else {
                applications = applicationRepository.findByFestivalId(festivalId);
            }
        } else if (clubId != null) {
            // Организатор может фильтровать по клубу, но видит только заявки своих фестивалей
            List<Application> allByClub = applicationRepository.findByClubId(clubId);
            applications = allByClub.stream()
                .filter(app -> app.getFestival().getOrganizer().getUser().getId().equals(organizerId))
                .toList();
        } else if (status != null) {
            // Показываем все заявки статуса, но только своих фестивалей
            applications = applicationRepository.findByOrganizerIdAndStatus(organizerId, status);
        } else {
            // Показываем все заявки своих фестивалей
            applications = applicationRepository.findByOrganizerIdAllApplications(organizerId);
        }

        return applications;
    }

    private List<Application> getClubApplications(
            String clubUserId,
            String clubId,
            ApplicationStatus status) {
        
        Club club = clubRepository.findByUserId(clubUserId)
            .orElseThrow(() -> new ResourceNotFoundException("Club for user ID: " + clubUserId + " not found"));

        // Если указан clubId, проверяем, что это клуб пользователя
        if (clubId != null && !clubId.equals(club.getId())) {
            throw new UnauthorizedAccessException("You don't have access to club ID: " + clubId);
        }

        List<Application> applications;

        if (status != null) {
            applications = applicationRepository.findByClubId(club.getId())
                .stream()
                .filter(app -> app.getStatus() == status)
                .toList();
        } else {
            applications = applicationRepository.findByClubId(club.getId());
        }

        return applications;
    }

    @Transactional(readOnly = true)
    public ApplicationDto getApplicationById(String applicationId) {
        Application application = getApplicationOrThrow(applicationId);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto createApplication(String userId, CreateApplicationRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found"));

        if (user.getRole() != UserRole.CLUB) {
            throw new UnauthorizedAccessException("Only users with CLUB role can submit applications. User ID: " + userId);
        }

        Club club = clubRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Club for user ID: " + userId + " not found"));

        Festival festival = festivalRepository.findById(request.getFestivalId())
            .orElseThrow(() -> new ResourceNotFoundException("Festival with ID: " + request.getFestivalId() + " not found"));

        if (festival.getStatus() == FestivalStatus.COMPLETED || festival.getStatus() == FestivalStatus.CANCELLED) {
            throw new ValidationException("Applications for festival with ID " + festival.getId() + " are closed. Current status: " + festival.getStatus());
        }

        if (applicationRepository.existsByFestivalIdAndClubId(festival.getId(), club.getId())) {
            throw new ValidationException("Application already submitted for festival ID " + festival.getId() + " and club ID " + club.getId());
        }

        Application application = Application.builder()
            .festival(festival)
            .club(club)
            .status(ApplicationStatus.PENDING)
            .description(request.getDescription())
            .submittedAt(LocalDateTime.now())
            .build();

        Application saved = applicationRepository.save(application);
        return applicationMapper.toDto(saved);
    }

    public ApplicationDto updateStatus(String applicationId, UpdateApplicationStatusRequest request) {
        Application application = getApplicationOrThrow(applicationId);

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new ValidationException("Application with ID " + applicationId + " has already been reviewed. Current status: " + application.getStatus());
        }

        application.setStatus(ApplicationStatus.valueOf(request.getStatus()));
        application.setReviewerNotes(request.getReviewerNotes());
        application.setReviewedAt(LocalDateTime.now());

        Application saved = applicationRepository.save(application);
        return applicationMapper.toDto(saved);
    }

    private Application getApplicationOrThrow(String applicationId) {
        return applicationRepository.findById(applicationId)
            .orElseThrow(() -> new ResourceNotFoundException("Application with ID: " + applicationId + " not found"));
    }
}
