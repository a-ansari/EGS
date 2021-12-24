package com.energizeglobal.atm.controllers.models.responses;

import java.util.HashMap;

public class UnsuccessfulResponse extends BaseResponse {
    public UnsuccessfulResponse(String exceptionClass) {
        response = "ERROR";
        this.exceptionClass = exceptionClass;
    }

    public UnsuccessfulResponse addError(String key, String value) {
        if (errors == null) {
            errors = new HashMap<>();
        }
        errors.put(key, value);
        return this;
    }
}
