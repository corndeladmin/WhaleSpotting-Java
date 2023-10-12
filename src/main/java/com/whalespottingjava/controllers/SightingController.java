package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SightingController {
    private final SightingService sightingService;
    @Autowired
    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    //renders the add-sighting form
    @GetMapping("/add-whale-sighting")
    public String getAddSightingPage(Model model) {
        model.addAttribute("sighting", new Sighting());
        return "add_sighting";
    }

    //submits the add-sighting form data
    @PostMapping("/add-whale-sighting")
    public String submitSighting(@ModelAttribute Sighting sighting, Model model) {
        model.addAttribute("sighting", sighting);
        sightingService.addSighting(sighting);
        return "add_sighting_confirmation";
    }

    @GetMapping("/Sightings")
    public String getAllSighting(Model model) {
        model.addAttribute("sightings", sightingService.getAllSightings());
        return "sighting_test";
    }
}
