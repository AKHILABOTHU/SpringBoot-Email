package com.ddhq.SpringBootEmail.controller;

import com.ddhq.SpringBootEmail.constants.ApplicationConstants;
import com.ddhq.SpringBootEmail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/send-with-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendMailWithAttachment(
            @RequestParam("to") String to,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "body", required = false) String body,
            @RequestPart("file") MultipartFile file) {
        try {
            String mailSubject = (subject != null && !subject.isBlank()) ? subject : ApplicationConstants.DEFAULT_EMAIL_SUBJECT;
            String mailBody = (body != null && !body.isBlank()) ? body : ApplicationConstants.DEFAULT_EMAIL_BODY;
            emailService.sendEmailWithAttachment(to, mailSubject, mailBody, file);
            return "Mail with attachment sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send mail with attachment: " + e.getMessage();
        }
    }
}