package com.dubinchin.controller;

import org.springframework.web.bind.annotation.PostMapping;

import com.dubinchin.dto.LoginRequest;
import com.dubinchin.dto.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public interface AuthController {
    @Operation(summary = "Login with email")
    @PostMapping("/login")
    LoginResponse login(
            @RequestBody @Valid LoginRequest request
    );
}
