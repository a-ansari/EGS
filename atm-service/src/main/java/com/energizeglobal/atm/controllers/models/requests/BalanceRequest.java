package com.energizeglobal.atm.controllers.models.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BalanceRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;

    @NotNull
    private Long sessionId;
}
