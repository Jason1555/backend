package com.dubinchin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.dubinchin.dto.UserDto;

@RequestMapping("/api/users")
@Tag(name = "Users")
public interface UserController {
    
    @Operation(summary = "Get current user")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/me")
    UserDto getCurrentUser(@RequestHeader("X-User-Id") String userId);
}
