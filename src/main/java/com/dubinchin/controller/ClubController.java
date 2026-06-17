package com.dubinchin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.dubinchin.dto.ClubDto;
import com.dubinchin.dto.UpdateClubRequest;

@RequestMapping("/api/clubs")
@Tag(name = "Clubs")
public interface ClubController {
    
    @Operation(summary = "Get club by ID")
    @ApiResponse(responseCode = "200", description = "Club found")
    @ApiResponse(responseCode = "404", description = "Club not found")
    @GetMapping("/{id}")
    ClubDto getClub(@PathVariable String id);

    @Operation(summary = "Update club by ID")
    @ApiResponse(responseCode = "200", description = "Club updated")
    @ApiResponse(responseCode = "404", description = "Club not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PutMapping("/{id}")
    ClubDto updateClub(@PathVariable String id, @RequestBody @Valid UpdateClubRequest request);
}
