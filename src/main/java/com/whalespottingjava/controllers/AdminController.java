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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final SightingService sightingService;

    @Autowired
    public AdminController (SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @ModelAttribute("status")
    public ApprovalStatus[] status() {
        return ApprovalStatus.values();
    }


    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public String getAdminPage(Model model) {
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        AdminApprovalRequest list = new AdminApprovalRequest();
        model.addAttribute("list", list);
        model.addAttribute("flag", true);
        return "admin";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(@ModelAttribute("list") AdminApprovalRequest list, Model model) {
        model.addAttribute("list", list);
        model.addAttribute("flag", true);
        //model.addAttribute("sightings", sightingService.getAllPendingSightings());
        System.out.println("\n\n\nHERE");
        System.out.println(list.getId());
        System.out.println(list.getApproved());
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }

/*
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(List<AdminApprovalRequest> list, Model model) {
        model.addAttribute("AdminApprovalRequest", list);
        System.out.println(list);

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
    } */
}
