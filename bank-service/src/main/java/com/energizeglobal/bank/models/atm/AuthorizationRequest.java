package com.energizeglobal.bank.models.atm;

import com.energizeglobal.bank.enums.AuthorizationMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class AuthorizationRequest extends BaseUnauthorizedRequest {
    @NotNull
    @Size(min = 40, max = 40)
    private String hashCode;

    @NotNull
    private AuthorizationMethod authMethod;
}
