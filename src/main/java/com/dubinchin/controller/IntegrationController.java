package com.dubinchin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.dubinchin.dto.PhotographerDto;

@RequestMapping("/api/integration")
@Tag(name = "Integration")
public interface IntegrationController {
    
    @Operation(summary = "Get photographers for festival")
    @ApiResponse(responseCode = "200", description = "Photographers retrieved")
    @ApiResponse(responseCode = "404", description = "Festival not found")
    @GetMapping("/festivals/{festivalId}/photographers")
    List<PhotographerDto> getPhotographers(@PathVariable String festivalId);

    @Operation(summary = "Hire photographer for festival")
    @ApiResponse(responseCode = "200", description = "Photographer hired")
    @ApiResponse(responseCode = "404", description = "Festival or photographer not found")
    @ApiResponse(responseCode = "400", description = "Photographer already hired")
    @PostMapping("/festivals/{festivalId}/photographers/hire")
    void hirePhotographer(
        @PathVariable String festivalId,
        @RequestParam String photographerId
    );
}
