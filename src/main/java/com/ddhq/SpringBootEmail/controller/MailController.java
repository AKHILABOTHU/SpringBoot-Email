package com.ddhq.SpringBootEmail.controller;

import com.ddhq.SpringBootEmail.constants.ApplicationConstants;
import com.ddhq.SpringBootEmail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/send-template")
    public String sendTemplateMail(
            @RequestParam("to") String to,
            @RequestParam(value = "name", required = false, defaultValue = "Valued User") String name,
            @RequestParam(value = "headerTitle", required = false, defaultValue = "SpringBoot Notification") String headerTitle,
            @RequestParam(value = "message", required = false, defaultValue = "Welcome! This is an HTML template email rendered using Apache FreeMarker.") String message,
            @RequestParam(value = "details", required = false, defaultValue = "Your account updates and preferences have been processed.") String details) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("name", name);
            model.put("headerTitle", headerTitle);
            model.put("message", message);
            model.put("details", details);

            emailService.sendHtmlEmailWithTemplate(to, "FreeMarker Template Email Notification", model, "email-template.ftl");
            return "HTML Email with FreeMarker template sent successfully!";
        } catch (Exception e) {
            return "Failed to send HTML template email: " + e.getMessage();
        }
    }
}