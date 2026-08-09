package com.ddhq.SpringBootEmail.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
        // Private constructor to prevent instantiation
    }

    // Email Server Configuration Constants
    public static final String MAIL_HOST = "smtp.gmail.com";
    public static final int MAIL_PORT = 587;
    public static final String MAIL_USERNAME = "[EMAIL_ADDRESS]";
    public static final String MAIL_PASSWORD = "[PASSWORD]";
    public static final String MAIL_PROTOCOL = "smtp";
    public static final String MAIL_DEFAULT_ENCODING = "UTF-8";

    // JavaMail SMTP Property Keys
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    public static final String MAIL_SMTP_CONNECTIONTIMEOUT = "mail.smtp.connectiontimeout";
    public static final String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
    public static final String MAIL_SMTP_WRITETIMEOUT = "mail.smtp.writetimeout";

    // JavaMail SMTP Property Values
    public static final String MAIL_SMTP_AUTH_VALUE = "true";
    public static final String MAIL_SMTP_STARTTLS_ENABLE_VALUE = "true";
    public static final String MAIL_SMTP_STARTTLS_REQUIRED_VALUE = "true";
    public static final String MAIL_SMTP_CONNECTIONTIMEOUT_VALUE = "5000";
    public static final String MAIL_SMTP_TIMEOUT_VALUE = "5000";
    public static final String MAIL_SMTP_WRITETIMEOUT_VALUE = "5000";

    // Default Email Messaging Constants
    public static final String DEFAULT_SENDER_EMAIL = "abothuakhil@gmail.com";
    public static final String DEFAULT_EMAIL_SUBJECT = "Test Mail from Spring Boot";
    public static final String DEFAULT_EMAIL_BODY = "Hello! This is a test email 🚀";
}
