package com.energizeglobal.bank.handlers;

import com.energizeglobal.bank.exceptions.BankException;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.models.response.UnsuccessfulResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        UnsuccessfulResponse response = new UnsuccessfulResponse("ValidationException");
        response.setErrors(errors);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMostSpecificCause().getMessage();
        return new UnsuccessfulResponse("MessageNotReadableException").addError("message", errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handleBankExceptions(BankException ex) {
        String errorMessage = ex.getMessage();
        return new UnsuccessfulResponse(ex.getClass().getSimpleName()).addError("message", errorMessage);
    }
}
