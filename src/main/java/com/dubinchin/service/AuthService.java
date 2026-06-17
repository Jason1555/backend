package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dubinchin.dto.LoginRequest;
import com.dubinchin.dto.LoginResponse;
import com.dubinchin.dto.RegisterRequest;
import com.dubinchin.entity.Club;
import com.dubinchin.entity.Organizer;
import com.dubinchin.entity.User;
import com.dubinchin.entity.enums.UserRole;
import com.dubinchin.exception.ValidationException;
import com.dubinchin.mapper.UserMapper;
import com.dubinchin.repository.ClubRepository;
import com.dubinchin.repository.OrganizerRepository;
import com.dubinchin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrganizerRepository organizerRepository;
    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ValidationException("User with email " + request.getEmail() + " not found"));

        return new LoginResponse(userMapper.toDto(user), generateToken(user.getId()));
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ValidationException("User with email " + request.getEmail() + " already exists");
        }

        User user = User.builder()
            .email(request.getEmail())
            .name(request.getName())
            .role(UserRole.valueOf(request.getRole()))
            .build();

        userRepository.save(user);

        switch (user.getRole()) {
            case ORGANIZER -> {
                Organizer organizer = Organizer.builder().user(user).build();
                organizerRepository.save(organizer);
            }
            case CLUB -> {
                Club club = Club.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .logo("")
                    .owner(user)
                    .build();
                clubRepository.save(club);
            }
        }

        return new LoginResponse(userMapper.toDto(user), generateToken(user.getId()));
    }

    private String generateToken(String userId) {
        return "mock-jwt-token-" + userId;
    }
}
