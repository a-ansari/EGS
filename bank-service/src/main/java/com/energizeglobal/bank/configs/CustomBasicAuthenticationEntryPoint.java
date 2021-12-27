package com.energizeglobal.bank.configs;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void afterPropertiesSet() {
        setRealmName("EGS");
        super.afterPropertiesSet();
    }
}
