package com.example.PaymentAPI.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    R("Returned"), A("Accepted");

    private String  abbreviation;
}
