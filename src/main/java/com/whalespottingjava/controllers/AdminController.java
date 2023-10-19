package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.models.requests.AdminApprovalRequest;
import com.whalespottingjava.models.requests.AdminApprovalRequests;

import com.whalespottingjava.services.SightingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        AdminApprovalRequests form = new AdminApprovalRequests();
        model.addAttribute("form", form);
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(@ModelAttribute AdminApprovalRequests form, Model model) {
        int numbOfRequests = form.getAdminApprovalRequests().size();
        for (AdminApprovalRequest adminApprovalRequest:form.getAdminApprovalRequests()) {

            if (adminApprovalRequest.getId() == null || adminApprovalRequest.getApproved() == null) {
                continue;
            }
            else if (adminApprovalRequest.getApproved().equals("true") && adminApprovalRequest.getId() != null) {
                long sightingId = adminApprovalRequest.getId();
                sightingService.approveSightingById(sightingId);
            }
            else if (adminApprovalRequest.getApproved().equals("false") && adminApprovalRequest.getId() != null) {
                long sightingId = adminApprovalRequest.getId();
                sightingService.deleteRejectedPendingSighting(sightingId);
            }
        }
        model.addAttribute("form", form);
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }
}
