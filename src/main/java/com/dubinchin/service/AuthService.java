package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dubinchin.dto.LoginRequest;
import com.dubinchin.dto.LoginResponse;
import com.dubinchin.entity.User;
import com.dubinchin.exception.ValidationException;
import com.dubinchin.mapper.UserMapper;
import com.dubinchin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
}
