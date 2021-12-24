package com.energizeglobal.atm.clients.models;

import lombok.Data;

import java.util.Map;

@Data
public class BaseResponse {
    public static final BaseResponse TIMEOUT = new BaseResponse();

    static {
        TIMEOUT.response = "ERROR";
        TIMEOUT.exceptionClass = "TimeoutException";
    }

    protected String response;
    protected String exceptionClass;
    protected Map<String, String> errors;
    protected Map<String, Object> data;
}
