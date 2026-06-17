package com.dubinchin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.dubinchin.dto.CreateFestivalRequest;
import com.dubinchin.dto.FestivalDto;
import com.dubinchin.dto.UpdateFestivalRequest;
import com.dubinchin.entity.enums.FestivalStatus;

@RequestMapping("/api/festivals")
@Tag(name = "Festivals")
public interface FestivalController {
    
    @Operation(summary = "Get all festivals with optional filters")
    @ApiResponse(responseCode = "200", description = "Festivals retrieved")
    @GetMapping
    List<FestivalDto> getFestivals(
        @RequestParam(required = false) String epoch,
        @RequestParam(required = false) FestivalStatus status
    );

    @Operation(summary = "Get a festival by ID")
    @ApiResponse(responseCode = "200", description = "Festival found")
    @ApiResponse(responseCode = "404", description = "Festival not found")
    @GetMapping("/{id}")
    FestivalDto getFestival(@PathVariable String id);

    @Operation(summary = "Create a new festival")
    @ApiResponse(responseCode = "201", description = "Festival created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "403", description = "Unauthorized")
    @PostMapping
    FestivalDto createFestival(
        @RequestHeader("X-User-Id") String userId,
        @RequestBody @Valid CreateFestivalRequest request
    );

    @Operation(summary = "Update a festival")
    @ApiResponse(responseCode = "200", description = "Festival updated")
    @ApiResponse(responseCode = "404", description = "Festival not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PutMapping("/{id}")
    FestivalDto updateFestival(
        @PathVariable String id,
        @RequestBody @Valid UpdateFestivalRequest request
    );

    @Operation(summary = "Delete a festival")
    @ApiResponse(responseCode = "204", description = "Festival deleted")
    @ApiResponse(responseCode = "404", description = "Festival not found")
    @ApiResponse(responseCode = "400", description = "Cannot delete festival")
    @DeleteMapping("/{id}")
    void deleteFestival(@PathVariable String id);
}
