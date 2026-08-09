package com.ddhq.SpringBootEmail.config;

import com.ddhq.SpringBootEmail.constants.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(ApplicationConstants.MAIL_HOST);
        mailSender.setPort(ApplicationConstants.MAIL_PORT);
        mailSender.setUsername(ApplicationConstants.MAIL_USERNAME);
        mailSender.setPassword(ApplicationConstants.MAIL_PASSWORD);
        mailSender.setProtocol(ApplicationConstants.MAIL_PROTOCOL);
        mailSender.setDefaultEncoding(ApplicationConstants.MAIL_DEFAULT_ENCODING);

        Properties props = mailSender.getJavaMailProperties();
        props.put(ApplicationConstants.MAIL_SMTP_AUTH, ApplicationConstants.MAIL_SMTP_AUTH_VALUE);
        props.put(ApplicationConstants.MAIL_SMTP_STARTTLS_ENABLE, ApplicationConstants.MAIL_SMTP_STARTTLS_ENABLE_VALUE);
        props.put(ApplicationConstants.MAIL_SMTP_STARTTLS_REQUIRED, ApplicationConstants.MAIL_SMTP_STARTTLS_REQUIRED_VALUE);
        props.put(ApplicationConstants.MAIL_SMTP_CONNECTIONTIMEOUT, ApplicationConstants.MAIL_SMTP_CONNECTIONTIMEOUT_VALUE);
        props.put(ApplicationConstants.MAIL_SMTP_TIMEOUT, ApplicationConstants.MAIL_SMTP_TIMEOUT_VALUE);
        props.put(ApplicationConstants.MAIL_SMTP_WRITETIMEOUT, ApplicationConstants.MAIL_SMTP_WRITETIMEOUT_VALUE);

        return mailSender;
    }
}
