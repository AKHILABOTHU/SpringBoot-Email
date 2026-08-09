package com.ddhq.SpringBootKafkaEmail.controller;

import com.ddhq.SpringBootKafkaEmail.service.EmailService;
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
                "Test Mail from Spring Boot",
                "Hello! This is a test email 🚀"
        );
        return "Mail sent!";
    }
}