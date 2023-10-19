package com.whalespottingjava.controllers;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.services.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembersController {
    @Autowired
    private MemberDetailsService memberDetailsService;

    @GetMapping("/members")
    public String getMembersPage() {
        return "members";
    }

    @GetMapping("/members/myaccount")
    public String getMyAccountPage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Long memberId = memberDetails.getMember().getId();
        Member member = this.memberDetailsService.loadMemberById(memberId);

        model.addAttribute("member", member);

        return "my_account";
    }
}
