package com.energizeglobal.atm.handlers;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class ExceptionHandlingFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder.Default defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            try {
                String body = IOUtils.toString(response.body().asReader(Charset.defaultCharset()));
                throw new CustomFeignException(body);
            } catch (IOException e) {
            }
        }
        return defaultDecoder.decode(methodKey, response);
    }
}