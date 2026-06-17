package com.dubinchin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dubinchin.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/users")
@Tag(name = "Users")
public interface UserController {
    @Operation(summary = "Get current user")
    @ApiResponse(responseCode = "200")
    @GetMapping("/me")
    UserDto getCurrentUser(@RequestHeader("X-User-Id") String userId);
}
