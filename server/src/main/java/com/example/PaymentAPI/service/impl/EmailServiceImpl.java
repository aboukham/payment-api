package com.example.PaymentAPI.service.impl;

import com.example.PaymentAPI.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender  javaMailSender;
    @Value("${spring.mail.username}")
    private String  sendFrom;

    @Override
    public void sendNotification(String subject, String body, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(sendFrom);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new MailAuthenticationException("Email address is invalid, please try with valid email");
        }

    }
}
