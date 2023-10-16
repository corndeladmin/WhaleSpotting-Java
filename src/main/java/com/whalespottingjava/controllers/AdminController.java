package com.whalespottingjava.controllers;

import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final SightingService sightingService;

    @Autowired
    public AdminController (SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public String getAdminPage(Model model) {
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postSightings(Model model) {
        
        return "admin";
    }
}
