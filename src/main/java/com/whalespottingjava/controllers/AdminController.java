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

    @GetMapping("/admin2")
    @ResponseStatus(HttpStatus.OK)
    public String getAdminPage2(Model model) {
        model.addAttribute("sightings", sightingService.getAllPendingSightings());

        return "admin2";
    }

    @PostMapping("/admin2")
    public String getAdminPage3() {
        System.out.println("\n\n\n\n\n\n\n\n HERRRRREEEEEE \n\n\n\n\n\n\n\n\n\n\n");
        return "admin2";
    }



    @PostMapping("/admin")
    public String getAdminPage4() {
        return "admin";
    }

}
