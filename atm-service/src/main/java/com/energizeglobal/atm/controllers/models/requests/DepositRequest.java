package com.energizeglobal.atm.controllers.models.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class DepositRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;

    @NotNull
    private Long sessionId;

    @NotNull
    @Positive
    private Long amount;
}
