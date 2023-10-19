package com.whalespottingjava.controllers;

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
                System.out.println("continue");
                continue;
            }
            else if (adminApprovalRequest.getApproved().equals("true") && adminApprovalRequest.getId() != null) {
                System.out.println("update");
            }
            else if (adminApprovalRequest.getApproved().equals("false") && adminApprovalRequest.getId() != null) {
                System.out.println("delete");
            }
        }
        model.addAttribute("form", form);
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }
}
