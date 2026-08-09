package com.ddhq.SpringBootEmail.service;

import com.ddhq.SpringBootEmail.constants.ApplicationConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ApplicationConstants.DEFAULT_SENDER_EMAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String body, MultipartFile file) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(ApplicationConstants.DEFAULT_SENDER_EMAIL);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        if (file != null && !file.isEmpty()) {
            String filename = file.getOriginalFilename() != null ? file.getOriginalFilename() : "attachment";
            helper.addAttachment(filename, file);
        }

        mailSender.send(mimeMessage);
    }
}