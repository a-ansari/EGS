package com.energizeglobal.bank.models.atm;

import com.energizeglobal.bank.enums.AuthorizationMethod;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthorizationRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;

    @NotNull
    @Size(min = 40, max = 40)
    private String hashCode;

    @NotNull
    private AuthorizationMethod authMethod;
}
