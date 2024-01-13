package com.example.PaymentAPI.aop;

import com.example.PaymentAPI.dao.PaymentRepository;
import com.example.PaymentAPI.domain.Payment;
import com.example.PaymentAPI.domain.Status;
import com.example.PaymentAPI.dto.PaymentDto;
import com.example.PaymentAPI.service.IEmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailSenderAspect {
    public static final String RETURNED = "returned";
    public static final String ACCEPTED = "accepted";
    @Autowired
    private IEmailService   emailService;
    @Autowired
    PaymentRepository   paymentRepository;

    @Pointcut("@annotation(com.example.PaymentAPI.aop.customAnnotation.SendEmail)")
    public void execute(){}

    @After("execute()")
    public void send(JoinPoint joinPoint) throws MailAuthenticationException {
        final Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 1){
            PaymentDto paymentDto = (PaymentDto) args[0];
            String customerEmail = (String) args[1];
            Payment payment = paymentRepository.findById(paymentDto.getId()).get();
            String status = (payment.getStatus() == Status.R)? RETURNED : ACCEPTED;
            StringBuilder subject = new StringBuilder("Payment ");
            subject.append(status);
            StringBuilder body = new StringBuilder("Hi Dear Customer,\nI Hope this email finds you well." +
                    "I want to inform you that your payment which it's amount is " + paymentDto.getAmount() +
                    "$ has been ");
            body.append(status);
            body.append(".\nBest regards\nBank info");
            emailService.sendNotification(subject.toString(), body.toString(), customerEmail);

        }
    }
}
