package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    @GetMapping("/sightings")
    public String getAllSightings(Model model) {
        model.addAttribute("sightings", sightingService.getAllSightings());
        return "sighting_test";
    }


    //sightings_approved.html is a local file
    //if you want to use this feature please changed "return "sightings_approved";" to whatever your html file is
    //Model model is how the data is parsed
    //The name of the model that is parsed is "approvedSightings" and is filled with "sightingService.getAllApprovedSightings());"
    //So that in the html file it is parsed to you can use the list of all the approved sightings
    @GetMapping("/sightings/approved")
    public String getAllApprovedSightings(Model model) {
        model.addAttribute("approvedSightings", sightingService.getAllApprovedSightings());
        return "sightings_approved";
    }
}
