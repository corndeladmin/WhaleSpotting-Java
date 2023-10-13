package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.services.SightingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "Facilitates mass uploading of sightings to be approved")
    @PostMapping("/api/bulk")
    @ResponseStatus(HttpStatus.CREATED)
//    @io.swagger.v3.oas.annotations.parameters.RequestBody
    public void postBulkSightings(
            @RequestBody
            List<Sighting> sightings // TODO: 12/10/2023 JSON Hijacking?
    ) {
        this.sightingService.addBulkSightings(sightings);
    }
}
