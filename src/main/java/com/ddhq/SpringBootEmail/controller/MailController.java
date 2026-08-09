package com.ddhq.SpringBootEmail.controller;

import com.ddhq.SpringBootEmail.constants.ApplicationConstants;
import com.ddhq.SpringBootEmail.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public String sendMail(@RequestParam String to) {
        emailService.sendEmail(
                to,
                ApplicationConstants.DEFAULT_EMAIL_SUBJECT,
                ApplicationConstants.DEFAULT_EMAIL_BODY);
        return "Mail sent!";
    }
}