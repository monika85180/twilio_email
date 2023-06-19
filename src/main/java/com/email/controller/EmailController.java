package com.email.controller;

import com.email.payload.EmailRequest;
import com.email.service.SendGridEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final SendGridEmailService sendGridEmailService;

    @Autowired
    public EmailController(SendGridEmailService sendGridEmailService) {
        this.sendGridEmailService = sendGridEmailService;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws Exception {
        sendGridEmailService.sendEmail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getContent());
    }
}

