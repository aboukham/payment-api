package com.example.PaymentAPI.service.impl;

import com.example.PaymentAPI.dao.PaymentRepository;
import com.example.PaymentAPI.domain.Payment;
import com.example.PaymentAPI.domain.Status;
import com.example.PaymentAPI.dto.PaymentDto;
import com.example.PaymentAPI.service.IPaymentService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService {
    public static final String ACCEPTED = "accepted";
    public static final String RETURNED = "returned";
    @Autowired
    private PaymentRepository   repository;

    @Observed(name = "create.payment")
    @Override
    public void createPayment(PaymentDto paymentDto) {
        Payment payment = new Payment(paymentDto.getId(), LocalDateTime.now(), paymentDto.getAmount(), Status.A);
        repository.save(payment);
    }

    @Observed(name = "return.payment")
    @Override
    public void returnPayment(PaymentDto paymentDto) {
        Optional<Payment> optional = repository.findById(paymentDto.getId());
        if (optional.isEmpty())
            throw new NoSuchElementException();
        Payment payment = new Payment(paymentDto.getId(), LocalDateTime.now(), paymentDto.getAmount(), Status.R);
        repository.save(payment);
    }

    @Observed(name = "show.payment")
    @Override
    public String showPayment(long id) {
        Optional<Payment> optional = repository.findById(id);
        if (optional.isEmpty())
            throw new NoSuchElementException();
        Payment payment = optional.get();
        StringBuilder paymentState = new StringBuilder("The state of the payment: ");
        paymentState.append(payment.getAmount());
        paymentState.append(" is: ");
        String status = (payment.getStatus() == Status.A)? ACCEPTED : RETURNED;
        paymentState.append(status);

        return paymentState.toString();
    }
}
