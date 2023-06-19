package com.email.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendGridEmailService {

    @Value("${sendgrid.apiKey}")
    private String sendGridApiKey;

    public void sendEmail(String toEmail, String subject, String content) throws Exception {
        Email from = new Email("mrathod95m@gmail.com"); // Replace with your sender email address
        Email to = new Email(toEmail);
        Content messageContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, messageContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
            System.out.println("Email sent successfully");
        } else {
            throw new Exception("Failed to send email. Status code: " + response.getStatusCode());
        }
    }
}

