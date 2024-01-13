package com.example.PaymentAPI.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@ControllerAdvice
public class PaymentException {
    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<String> handleException(MailAuthenticationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleException(NoSuchElementException e){
        return new ResponseEntity<>("The payment not found please try with the correct Id", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
