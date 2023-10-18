package com.whalespottingjava.controllers;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.models.database.Sighting;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MembersController {
    @GetMapping("/members")
    public String getMembersPage() {
        return "members";
    }

    @GetMapping("/members/myaccount")
    public String getMyAccountPage() {
        return "my_account";
    }

    @PostMapping("/change-password")
    public String submitSighting(@ModelAttribute Member member, Model model, String oldPassword, String newPassword) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        if(memberDetails.getMember().getPassword().equals(oldPassword)){ //encrypted or normal?
            memberDetails.getMember().setPassword(newPassword);
        }
        return "my_account";
    }
}
