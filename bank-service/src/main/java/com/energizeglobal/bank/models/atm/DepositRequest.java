package com.energizeglobal.bank.models.atm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class DepositRequest extends BaseAuthorizedRequest {
    @NotNull
    @Positive
    private Long amount;
}
