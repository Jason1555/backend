package com.dubinchin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dubinchin.dto.ClubDto;
import com.dubinchin.dto.UpdateClubRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("/api/clubs")
@Tag(name = "Clubs")
public interface ClubController {
    @Operation(summary = "Get club by ID")
    @GetMapping("/{id}")
    ClubDto getClub(@PathVariable String id);

    @Operation(summary = "Update club by ID")
    @PutMapping("/{id}")
    ClubDto updateClub(@PathVariable String id, @RequestBody @Valid UpdateClubRequest request);
}
