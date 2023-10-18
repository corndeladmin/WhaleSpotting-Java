package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.models.requests.AdminApprovalRequest;
import com.whalespottingjava.models.requests.AdminApprovalRequests;

import com.whalespottingjava.services.SightingService;
import com.whalespottingjava.models.enums.ApprovalStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        AdminApprovalRequests form = new AdminApprovalRequests();
        //AdminApprovalRequest adminApproval =  new AdminApprovalRequest();
        model.addAttribute("form", form);

        return "admin";
    }


    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(@ModelAttribute("form") AdminApprovalRequests form, Model model) {
        //model.addAttribute("adminApproval", adminApproval);
        System.out.println(form.getAdminApprovalRequests());

        //System.out.println(adminApproval.getApproved());
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
