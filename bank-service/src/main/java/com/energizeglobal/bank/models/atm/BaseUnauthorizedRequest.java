package com.energizeglobal.bank.models.atm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class BaseUnauthorizedRequest extends BaseRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;
}
