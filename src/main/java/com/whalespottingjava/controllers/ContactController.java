package com.whalespottingjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.mail.javamail.JavaMailSender;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact_form";
    }

    @PostMapping("/contact")
    public String submitContact(HttpSerlvetRequest request){
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("whalespottingapp@gmail.com");
        message.setTo("whalespottingapp@gmail.com");

        String mailSubject=fullname+ "has sent a message";
        String mailContent="Sender name: "+ fullname+ "\n";
        mailContent+="Sender email: "+ email+ "\n";
        mailContent+="Sender subject: "+ subject+ "\n";
        mailContent+="Sender content: "+ content+ "\n";


        message.setSubject(mailSubject);
        message.setText(mailContent);

        mailSender.send(message);
        return "message";
    }
}
