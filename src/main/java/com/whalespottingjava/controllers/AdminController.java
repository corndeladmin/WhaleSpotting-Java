package com.whalespottingjava.controllers;

import com.whalespottingjava.models.requests.AdminApprovalRequests;
import com.whalespottingjava.models.requests.AdminApprovalRequest;
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
        long sightingId;
        for (AdminApprovalRequest adminApprovalRequest: form.getAdminApprovalRequests()) {
            if (adminApprovalRequest.getId() == null || adminApprovalRequest.getApproved() == null) {
                break;
                // The reason behind the first if is to highlight that if the user Admin does not select a radio button,
                // thymeleaf will return a null and for reading purpose to cover this case.
            }
            sightingId = adminApprovalRequest.getId();
            if (adminApprovalRequest.getApproved().equals("true")) {
                //sightingId = adminApprovalRequest.getId();
                sightingService.approveSightingById(sightingId);
            } else if (adminApprovalRequest.getApproved().equals("false")) {
                //sightingId = adminApprovalRequest.getId();
                sightingService.deleteRejectedPendingSighting(sightingId);
            }
        }

        model.addAttribute("form", form);
        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        return "admin";
    }
}
