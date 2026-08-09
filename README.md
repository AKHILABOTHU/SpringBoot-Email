# Spring Boot Email Application

A lightweight Spring Boot application for sending emails using `JavaMailSender` and Gmail SMTP integration.

---

## 🌿 Branch Overview

This repository demonstrates two different approaches to configuring email settings in Spring Boot across two branches:

| Branch | Description | Configuration Approach |
| :--- | :--- | :--- |
| **`main`** | Simple Spring Mail Configuration | Properties are added in `application.yaml`, leveraging Spring Boot auto-configuration for `JavaMailSender`. |
| **`Email-Configuration`** | Programmatic Configuration Class | Properties are managed in `ApplicationConstants.java` and explicitly configured via a `@Configuration` class (`EmailConfig.java`). |

### Switch Branches
```bash
# Switch to main branch (application.yaml configuration)
git checkout main

# Switch to Email-Configuration branch (Configuration class & ApplicationConstants)
git checkout Email-Configuration
```

---

## 📋 Table of Contents
- [Prerequisites](#-prerequisites)
- [🔑 How to Get a Gmail App Password](#-how-to-get-a-gmail-app-password)
- [⚙️ Configuration Modes](#️-configuration-modes)
  - [1. YAML Configuration (`main` branch)](#1-yaml-configuration-main-branch)
  - [2. Configuration Class (`Email-Configuration` branch)](#2-configuration-class-email-configuration-branch)
- [🚀 Running the Application](#-running-the-application)
- [🧪 Testing the Endpoint](#-testing-the-endpoint)
- [🛡️ Security Best Practices](#️-security-best-practices)

---

## 📌 Prerequisites
- **Java 17** or later installed
- **Maven** (or use the included `./mvnw` wrapper)
- A **Gmail account** with 2-Step Verification enabled

---

## 🔑 How to Get a Gmail App Password

Google no longer supports "Less Secure Apps" for authentication. You must use an **App Password** (a 16-digit passcode) to allow Spring Boot to connect to Gmail's SMTP server securely.

### Step 1: Enable 2-Step Verification
1. Open your Google Account page: [https://myaccount.google.com/](https://myaccount.google.com/).
2. In the left navigation menu, click **Security**.
3. Under the **"How you sign in to Google"** section, ensure **2-Step Verification** is turned **ON**. (If not, follow the prompts to set it up).

### Step 2: Generate an App Password
1. Go directly to [https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords) (or search for **"App passwords"** in the top search bar of your Google Account).
2. Enter an **App name** (e.g., `SpringBootEmail`).
3. Click **Create**.
4. Google will display a **16-character passcode** (e.g., `abcd efgh ijkl mnop`).
5. **Copy this passcode** immediately. *(Note: You will use this code as your mail password without spaces).*

---

## ⚙️ Configuration Modes

### 1. YAML Configuration (`main` branch)

In the `main` branch, email properties are defined directly in `src/main/resources/application.yaml`. Spring Boot automatically creates and wires the `JavaMailSender` bean based on these properties.

```yaml
spring:
  application:
    name: SpringBootEmail
  mail:
    host: smtp.gmail.com
    port: 587
    username: YOUR_GMAIL_ADDRESS@gmail.com
    password: YOUR_16_DIGIT_APP_PASSWORD
    protocol: smtp
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
          connectiontimeout: 5000
          timeout: 5000
          writetimeout: 5000
```

---

### 2. Configuration Class (`Email-Configuration` branch)

In the `Email-Configuration` branch, email properties and SMTP parameters are loaded programmatically via a Java configuration class (`EmailConfig.java`) and `ApplicationConstants.java`:

- **`ApplicationConstants.java`**: Holds email server parameters (`MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`, etc.) and SMTP property keys/values.
- **`EmailConfig.java`**: Custom `@Configuration` class defining the `JavaMailSender` bean explicitly:

```java
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
```

---

## 🚀 Running the Application

Run the application using the Maven wrapper:

### Windows (PowerShell / CMD)
```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS
```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

---

## 🧪 Testing the Endpoint

Send a test email by hitting the REST endpoint via browser or `curl`:

```bash
curl "http://localhost:8080/mail/send?to=recipient@example.com"
```

**Expected Response:**
```text
Mail sent!
```

---

## 🛡️ Security Best Practices
- ⚠️ **Never commit your actual Gmail App Password** or personal email to public repositories.
- Use placeholder values or environment variables for production credentials in version control.
- Revoke App Passwords anytime under [Google Account Security](https://myaccount.google.com/apppasswords) if compromised.
