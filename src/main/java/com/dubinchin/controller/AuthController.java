package com.dubinchin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.dubinchin.dto.LoginRequest;
import com.dubinchin.dto.LoginResponse;
import com.dubinchin.dto.RegisterRequest;

@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public interface AuthController {
    
    @Operation(summary = "Login with email")
    @PostMapping("/login")
    LoginResponse login(@RequestBody @Valid LoginRequest request);

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    LoginResponse register(@RequestBody @Valid RegisterRequest request);
}
