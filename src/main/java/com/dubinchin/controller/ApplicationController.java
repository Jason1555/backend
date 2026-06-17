package com.dubinchin.controller;

import com.dubinchin.dto.ApplicationDto;
import com.dubinchin.dto.CreateApplicationRequest;
import com.dubinchin.dto.UpdateApplicationStatusRequest;
import com.dubinchin.entity.enums.ApplicationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/applications")
@Tag(name = "Applications")
public interface ApplicationController {

    @Operation(summary = "Get applications")
    @GetMapping
    List<ApplicationDto> getApplications(@RequestParam(required = false) String festivalId, @RequestParam(required = false) String clubId, @RequestParam(required = false) ApplicationStatus status);

    @Operation(summary = "Get application by ID")
    @GetMapping("/{id}")
    ApplicationDto getApplication(@PathVariable String id);

    @Operation(summary = "Submit application")
    @PostMapping
    ApplicationDto createApplication(@RequestHeader("X-User-Id") String userId,@RequestBody @Valid CreateApplicationRequest request);

    @Operation(summary = "Update application status")
    @PutMapping("/{id}/status")
    ApplicationDto updateStatus(@PathVariable String id, @RequestBody @Valid UpdateApplicationStatusRequest request);
}
