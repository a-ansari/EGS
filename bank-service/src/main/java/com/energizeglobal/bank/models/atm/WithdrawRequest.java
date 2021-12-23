package com.energizeglobal.bank.models.atm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class WithdrawRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;

    @NotNull
    private Long sessionId;

    @NotNull
    @Positive
    private Long amount;
}
