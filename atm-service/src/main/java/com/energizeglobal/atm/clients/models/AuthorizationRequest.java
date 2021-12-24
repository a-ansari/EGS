package com.energizeglobal.atm.clients.models;

import lombok.Data;

@Data
public class AuthorizationRequest {
    private String cardNumber;
    private String hashCode;
    private AuthorizationMethod authMethod;
}
