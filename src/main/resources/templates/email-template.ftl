<#include "header.ftl">

<h2 style="color: #1f2937; margin-top: 0;">Hello, ${name!"Valued User"}! 👋</h2>

<p style="font-size: 16px; color: #4b5563;">
    ${message!"Thank you for using our service. We are pleased to provide you with this update."}
</p>

<div style="background-color: #f0f9ff; border-left: 4px solid #0284c7; padding: 15px; margin: 20px 0; border-radius: 4px;">
    <strong style="color: #0369a1;">Notification Details:</strong>
    <p style="margin: 5px 0 0 0; color: #334155; font-size: 14px;">
        ${details!"Your action was completed successfully."}
    </p>
</div>

<p style="font-size: 14px; color: #6b7280; margin-top: 25px;">
    Best regards,<br>
    <strong>The SpringBoot Team</strong>
</p>

<#include "footer.ftl">
