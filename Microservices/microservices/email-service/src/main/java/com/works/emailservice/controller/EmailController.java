package com.works.emailservice.controller;

import com.works.emailservice.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public HttpStatus sendEmail(@RequestParam String emailTo, String title, String message) {
        emailService.sendMail(emailTo, title, message);
        return HttpStatus.OK;
    }
}
