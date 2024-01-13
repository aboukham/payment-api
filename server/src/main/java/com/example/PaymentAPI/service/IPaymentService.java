package com.example.PaymentAPI.service;

import com.example.PaymentAPI.domain.Payment;
import com.example.PaymentAPI.dto.PaymentDto;

public interface IPaymentService {
    public void createPayment(PaymentDto payment);
    public void returnPayment(PaymentDto paymentDto);
    public String showPayment(long id);
}
