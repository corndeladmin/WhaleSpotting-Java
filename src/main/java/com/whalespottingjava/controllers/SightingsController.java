package com.whalespottingjava.controllers;

import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SightingsController {
    private final SightingService sightingService;
    @Autowired
    public SightingsController(SightingService sightingService) {
        this.sightingService = sightingService;
    }
    @GetMapping("/add-whale-sighting")
    public String getAddSightingPage() {
        return "add_sighting";
    }

    @GetMapping("/sightings")
    public String getSightingsPage() {
        return "sightings";
    }

//    @GetMapping("/sightings")
//    public String getAllSightings(Model model) {
//        model.addAttribute("sightings", sightingService.getAllSightings());
//        return "sighting_test";
//    }
}
