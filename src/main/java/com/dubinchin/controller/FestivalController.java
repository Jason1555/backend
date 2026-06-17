package com.dubinchin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dubinchin.dto.CreateFestivalRequest;
import com.dubinchin.dto.FestivalDto;
import com.dubinchin.dto.UpdateFestivalRequest;
import com.dubinchin.entity.enums.FestivalStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("/api/festivals")
@Tag(name = "Festivals") 
public interface FestivalController {
    @Operation(summary = "Get all festivals")
    @GetMapping
    List<FestivalDto> getFestivals(

            @RequestParam(required = false)
            String epoch,

            @RequestParam(required = false)
            FestivalStatus status
    );

    @Operation(summary = "Get a festival by ID")
    @GetMapping("/{id}")
    FestivalDto getFestival(
            @PathVariable String id
    );

    @Operation(summary = "Create a festival")
    @PostMapping
    FestivalDto createFestival(

            @RequestHeader("X-User-Id")
            String userId,

            @RequestBody @Valid
            CreateFestivalRequest request
    );

    @Operation(summary = "Update a festival")
    @PutMapping("/{id}")
    FestivalDto updateFestival(

            @PathVariable
            String id,

            @RequestBody @Valid
            UpdateFestivalRequest request
    );

    @Operation(summary = "Delete a festival")
    @DeleteMapping("/{id}")
    void deleteFestival(@PathVariable String id);
}
