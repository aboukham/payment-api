package com.example.PaymentApiClient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentDto {
    private long id;
    private BigDecimal amount;
}
