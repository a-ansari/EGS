package com.energizeglobal.atm.controllers.models.requests;

import com.energizeglobal.atm.clients.models.AuthorizationMethod;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthorizationRequest {
    @NotNull
    @Size(min = 16, max = 24)
    private String cardNumber;

    @NotNull
    private String plainPassword;

    @NotNull
    private AuthorizationMethod authMethod;
}
