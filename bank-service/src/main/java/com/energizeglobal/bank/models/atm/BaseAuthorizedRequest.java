package com.energizeglobal.bank.models.atm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BaseAuthorizedRequest extends BaseUnauthorizedRequest {
    @NotNull
    private Long sessionId;
}
