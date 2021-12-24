package com.energizeglobal.atm.clients.models;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String cardNumber;
    private Long sessionId;
    private Long amount;
}
