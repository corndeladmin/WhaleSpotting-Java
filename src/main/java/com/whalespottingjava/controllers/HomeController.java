package com.whalespottingjava.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.enums.MemberRole;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Boolean isLoggedIn = authentication.isAuthenticated();
        model.addAttribute("isLoggedIn", isLoggedIn);
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        //use ternary operator
        MemberRole role = memberDetails.getMember().getRole();
        if (role == MemberRole.ADMIN) {
            model.addAttribute("role", role );
        }
        return "home";
    }
}
