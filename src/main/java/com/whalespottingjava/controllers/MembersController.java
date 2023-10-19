package com.whalespottingjava.controllers;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.services.MemberUpdateService;
import com.whalespottingjava.util.AuthenticationHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.whalespottingjava.services.MemberDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MembersController {
    private final MemberUpdateService memberUpdateService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public MembersController(
        AuthenticationHelper authenticationHelper,
        MemberUpdateService memberUpdateService
    ) {
        this.authenticationHelper = authenticationHelper;
        this.memberUpdateService = memberUpdateService;
    }

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

    @PostMapping("/members/myaccount/change-password")
    public String submitSighting(Model model, String oldPassword, String newPassword, RedirectAttributes redirectAttributes) {
        BCryptPasswordEncoder encoder = this.authenticationHelper.passwordEncoder();
        String encodeNewPassword = encoder.encode(newPassword);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Member member;

        if (encoder.matches(oldPassword, memberDetails.getPassword())) {
            member = memberDetails.getMember();
            member.setPassword(encodeNewPassword);
            this.memberUpdateService.updatePassword(member);

            redirectAttributes.addFlashAttribute("isValid", true);
        } else {
            redirectAttributes.addFlashAttribute("isValid", false);
        }
        
        return "redirect:/members/myaccount";
    }
}
