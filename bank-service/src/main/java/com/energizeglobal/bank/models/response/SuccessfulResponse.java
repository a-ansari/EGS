package com.energizeglobal.bank.models.response;

import java.util.HashMap;

public class SuccessfulResponse extends BaseResponse {
    public static final SuccessfulResponse OK = new SuccessfulResponse();

    public SuccessfulResponse() {
        response = "OK";
    }

    public SuccessfulResponse addData(String key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, value);
        return this;
    }
}
