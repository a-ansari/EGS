package com.energizeglobal.atm.controllers.models.responses;

import lombok.Data;

import java.util.Map;

@Data
public class BaseResponse {
    protected String response;
    protected String exceptionClass;
    protected Map<String, String> errors;
    protected Map<String, Object> data;

    public static BaseResponse fromClientResponse(com.energizeglobal.atm.clients.models.BaseResponse request) {
        BaseResponse newResponse = new BaseResponse();
        newResponse.response = request.getResponse();
        newResponse.exceptionClass = request.getExceptionClass();
        newResponse.errors = request.getErrors();
        newResponse.data = request.getData();
        return newResponse;
    }
}
