package com.energizeglobal.bank.models.response;

import lombok.Data;

import java.util.Map;

@Data
public abstract class BaseResponse {
    protected String response;
    protected String exceptionClass;
    protected Map<String, String> errors;
    protected Map<String, Object> data;

}
