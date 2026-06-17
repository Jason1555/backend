package com.dubinchin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dubinchin.dto.PhotographerDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/integration")
@Tag(name = "Integration")
public interface IntegrationController {
    @Operation(summary = "Получить фотографов фестиваля")
    @GetMapping("/festivals/{festivalId}/photographers")
    List<PhotographerDto> getPhotographers(@PathVariable String festivalId);

    @Operation(summary = "Нанять фотографа")
    @PostMapping("/festivals/{festivalId}/photographers/hire")
    void hirePhotographer(@PathVariable String festivalId, @RequestParam String photographerId);
}
