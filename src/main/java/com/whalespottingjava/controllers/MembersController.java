package com.whalespottingjava.controllers;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.util.AuthenticationHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MembersController {

    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public MembersController(
        AuthenticationHelper authenticationHelper
    ) {
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping("/members")
    public String getMembersPage() {
        return "members";
    }

    @GetMapping("/members/myaccount")
    public String getMyAccountPage() {
        return "my_account";
    }

    @PostMapping("/change-password")
    public String submitSighting(Model model, String oldPassword, String newPassword) {
        BCryptPasswordEncoder encoder = authenticationHelper.passwordEncoder();
        String encodeNewPassword = encoder.encode(newPassword);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        if (encoder.matches(oldPassword, memberDetails.getPassword())) {
            memberDetails.getMember().setPassword(encodeNewPassword);
        }
        
        return "redirect:/my_account";
    }
}
