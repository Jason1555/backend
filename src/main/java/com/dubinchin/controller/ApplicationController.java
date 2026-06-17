package com.dubinchin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.dubinchin.dto.ApplicationDto;
import com.dubinchin.dto.CreateApplicationRequest;
import com.dubinchin.dto.UpdateApplicationStatusRequest;
import com.dubinchin.entity.enums.ApplicationStatus;

@RequestMapping("/api/applications")
@Tag(name = "Applications")
public interface ApplicationController {

    @Operation(summary = "Get applications with optional filters")
    @ApiResponse(responseCode = "200", description = "Applications retrieved")
    @GetMapping
    List<ApplicationDto> getApplications(
        @RequestHeader("X-User-Id") String userId,
        @RequestParam(required = false) String festivalId,
        @RequestParam(required = false) String clubId,
        @RequestParam(required = false) ApplicationStatus status
    );

    @Operation(summary = "Get application by ID")
    @ApiResponse(responseCode = "200", description = "Application found")
    @ApiResponse(responseCode = "404", description = "Application not found")
    @GetMapping("/{id}")
    ApplicationDto getApplication(@PathVariable String id);

    @Operation(summary = "Submit application")
    @ApiResponse(responseCode = "201", description = "Application created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "403", description = "Unauthorized")
    @PostMapping
    ApplicationDto createApplication(
        @RequestHeader("X-User-Id") String userId,
        @RequestBody @Valid CreateApplicationRequest request
    );

    @Operation(summary = "Update application status")
    @ApiResponse(responseCode = "200", description = "Application updated")
    @ApiResponse(responseCode = "404", description = "Application not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PutMapping("/{id}/status")
    ApplicationDto updateStatus(
        @PathVariable String id,
        @RequestBody @Valid UpdateApplicationStatusRequest request
    );
}
