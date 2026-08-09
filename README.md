# Spring Boot Email Application

A lightweight Spring Boot application for sending emails using `JavaMailSender` and Gmail SMTP integration.

---

## 📋 Table of Contents
- [Prerequisites](#-prerequisites)
- [🔑 How to Get a Gmail App Password](#-how-to-get-a-gmail-app-password)
- [⚙️ Configuration](#️-configuration)
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
5. **Copy this passcode** immediately. *(Note: You will use this code as your `spring.mail.password` without spaces).*

---

## ⚙️ Configuration

The email configuration is located in `src/main/resources/application.yaml`.

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

### 💡 Using Environment Variables (Recommended)
To keep your sensitive credentials safe and out of version control, configure `application.yaml` to read from environment variables:

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${SPRING_MAIL_USERNAME}
    password: ${SPRING_MAIL_PASSWORD}
```

Then set environment variables in your terminal or IDE:
- **Windows PowerShell**:
  ```powershell
  $env:SPRING_MAIL_USERNAME="your-email@gmail.com"
  $env:SPRING_MAIL_PASSWORD="your-16-digit-app-password"
  ```
- **Linux/macOS**:
  ```bash
  export SPRING_MAIL_USERNAME="your-email@gmail.com"
  export SPRING_MAIL_PASSWORD="your-16-digit-app-password"
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
- Use `.gitignore` to prevent committing configuration files containing secrets.
- Revoke App Passwords anytime under [Google Account Security](https://myaccount.google.com/apppasswords) if they are compromised.
