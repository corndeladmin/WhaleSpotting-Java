package com.whalespottingjava.controllers;

import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SightingController {
    private final SightingService sightingService;

    @Autowired
    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping("/add-whale-sighting")
    public String getAddSightingPage() {
        return "add_sighting";
    }

//    @GetMapping("/Sightings")
//    public String getAllSighting(Model model) {
//        model.addAttribute("sightings", sightingService.getAllSightings());
//        return "sighting_test";
//    }
//
//    @GetMapping("/sightings/approved")
//    public String getAllApprovedSightings(Model model) {
//        model.addAttribute("approvedSightings", this.sightingService.getAllApprovedSightings());
//        return "sightings_approved";
//    }

    @GetMapping("/Sightings")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void getAllSighting() {
    }

    @GetMapping("/sightings/approved")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void getAllApprovedSightings() {
    }
}
