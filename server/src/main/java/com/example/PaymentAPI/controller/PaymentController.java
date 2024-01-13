package com.example.PaymentAPI.controller;

import com.example.PaymentAPI.aop.customAnnotation.SendEmail;
import com.example.PaymentAPI.dto.PaymentDto;
import com.example.PaymentAPI.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private IPaymentService     paymentService;

    @SendEmail
    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentDto paymentDto,
                                             @RequestParam(name = "email", required = true) String email)
    {
        paymentService.createPayment(paymentDto);
        return new ResponseEntity<>("payment saved successfully", HttpStatus.OK);
    }

    @SendEmail
    @PutMapping("/return")
    public ResponseEntity<String> returnPayment(@RequestBody PaymentDto paymentDto,
                                                @RequestParam(name = "email", required = true) String email)
                                                {
        paymentService.returnPayment(paymentDto);
        return new ResponseEntity<>("return payment accepted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> showPayment(@PathVariable long id) throws NullPointerException{
        String paymentState = paymentService.showPayment(id);
        return new ResponseEntity<>(paymentState, HttpStatus.OK);
    }


}
