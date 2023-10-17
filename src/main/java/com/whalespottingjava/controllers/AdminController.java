package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.models.requests.AdminApprovalRequest;
import com.whalespottingjava.services.SightingService;

import com.whalespottingjava.models.enums.ApprovalStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import java.util.Optional;

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
    public String getAdminPage(Model model, ApprovalStatus status) {
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        model.addAttribute("status", status);
        return "admin";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(@RequestBody List<AdminApprovalRequest> list, Model model) {
        for (AdminApprovalRequest sighting: list) {
            if (sighting.getApproved() == false) {
                sightingService.deleteRejectedPendingSighting(sighting.getId());
            }
            if (sighting.getApproved() == true) {
                Sighting currentSighting = sightingService.getSightingById(sighting.getId());
                sightingService.addSighting(currentSighting);
            }
            if (sighting.getApproved() == null) {
                continue;
            }
        }
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }
}
