package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.services.SightingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private final SightingService sightingService;

    @Autowired
    public ApiController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @Operation(description = "Returns every single approved whale sighting")
    @GetMapping("/api/get-all")
    @ResponseBody
    public List<Sighting> getAllSightings() {
        return this.sightingService.getAllApprovedSightings();
    }
}
