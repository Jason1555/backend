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
import com.dubinchin.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrganizerRepository organizerRepository;
    private final ClubRepository clubRepository;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ValidationException(
                        "User with email " + request.getEmail() + " not found"
                ));

        return new LoginResponse(
                userMapper.toDto(user),
                "mock-jwt-token-" + user.getId()
        );
    }

    public LoginResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ValidationException(
                    "Пользователь с таким email уже существует"
            );
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(UserRole.valueOf(request.getRole()));

        userRepository.save(user);

        switch (UserRole.valueOf(request.getRole())) {

            case UserRole.ORGANIZER -> {
                Organizer organizer = new Organizer();
                organizer.setUser(user);

                organizerRepository.save(organizer);
            }

            case UserRole.CLUB -> {
                Club club = new Club();

                club.setName(request.getName());
                club.setEmail(request.getEmail());
                club.setLogo("");
                club.setOwner(user);

                clubRepository.save(club);
            }
        }

        return buildResponse(user);
    }

    private LoginResponse buildResponse(User user) {

        return new LoginResponse(
                userMapper.toDto(user),
                "mock-jwt-token-" + user.getId()
        );
    }
}
