package com.energizeglobal.atm.clients.models;

import lombok.Data;

@Data
public class DepositRequest {
    private String cardNumber;
    private Long sessionId;
    private Long amount;
}
