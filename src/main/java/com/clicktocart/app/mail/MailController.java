package com.clicktocart.app.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mail/send")
public class MailController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping
    public void sendMessage(@RequestBody SendEmail sendEmail){
        sendEmailService.sendMessage(sendEmail.getTo(),sendEmail.getSubject(),sendEmail.getText());
    }

}
