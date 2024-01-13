package com.example.PaymentAPI.service;

public interface IEmailService {
    public void sendNotification(String subject, String body, String toEmail);
}
