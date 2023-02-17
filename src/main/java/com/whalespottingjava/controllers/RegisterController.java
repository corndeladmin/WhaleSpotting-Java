package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.models.requests.MemberRegistrationRequest;
import com.whalespottingjava.services.MemberRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private MemberRegistrationService memberRegistrationService;

    @Autowired
    public RegisterController(MemberRegistrationService memberRegistrationService) {
        this.memberRegistrationService = memberRegistrationService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("member", new MemberRegistrationRequest());
        return "register";
    }

    @PostMapping("/register")
    // TODO 409: @Valid on request here?
    public String onRegistrationSubmit(
            @ModelAttribute MemberRegistrationRequest memberRegistrationRequest
    ) {
        try {
            Member member = memberRegistrationService.registerMember(memberRegistrationRequest);
        } catch (Exception e) {
            // TODO 409: Pass error back to display somehow
        }

        return "home"; // todo 409: Make this a redirect
    }
}
