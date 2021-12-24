package com.energizeglobal.atm.handlers;

public class CustomFeignException extends RuntimeException {
    private String body;

    public CustomFeignException(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

}
