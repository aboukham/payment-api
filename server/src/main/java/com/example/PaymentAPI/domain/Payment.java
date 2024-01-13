package com.example.PaymentAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment Records")

//FINAL
public class Payment {
    @Id
    private long    id;
    private LocalDateTime date;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Status  status;
}
