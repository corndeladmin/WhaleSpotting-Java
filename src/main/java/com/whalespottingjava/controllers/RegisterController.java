package com.whalespottingjava.controllers;

import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.models.requests.MemberRegistrationRequest;
import com.whalespottingjava.services.MemberLoginService;
import com.whalespottingjava.services.MemberRegistrationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterController {
    private MemberRegistrationService memberRegistrationService;
    private MemberLoginService memberLoginService;

    @Autowired
    public RegisterController(
            MemberRegistrationService memberRegistrationService,
            MemberLoginService memberLoginService
    ) {
        this.memberRegistrationService = memberRegistrationService;
        this.memberLoginService = memberLoginService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("member", new MemberRegistrationRequest());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView onRegistrationSubmit(
            @ModelAttribute MemberRegistrationRequest memberRegistrationRequest,
            HttpServletRequest servletRequest
    ) {
        try {
            memberRegistrationService.registerMember(memberRegistrationRequest);
            memberLoginService.loginMember(
                    servletRequest,
                    memberRegistrationRequest.getUsername(),
                    memberRegistrationRequest.getPassword()
            );
        } catch (ServletException e) {
            // TODO 409: handle login error
        } catch (Exception e) {
            // TODO 409: Pass error back to display somehow
        }

        return new RedirectView("members");
    }
}
