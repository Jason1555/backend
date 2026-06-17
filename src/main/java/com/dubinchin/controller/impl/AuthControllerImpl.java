package com.dubinchin.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.dubinchin.controller.AuthController;
import com.dubinchin.dto.LoginRequest;
import com.dubinchin.dto.LoginResponse;
import com.dubinchin.dto.RegisterRequest;
import com.dubinchin.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public LoginResponse login(LoginRequest request) {
        return authService.login(request);
    }

    @Override
    public LoginResponse register(RegisterRequest request) {
        return authService.register(request);
    }
}
