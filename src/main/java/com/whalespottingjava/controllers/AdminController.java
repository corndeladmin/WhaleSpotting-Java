package com.whalespottingjava.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}
