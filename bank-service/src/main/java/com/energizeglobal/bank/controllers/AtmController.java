package com.energizeglobal.bank.controllers;

import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.services.AtmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/atm")
public class AtmController {
    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/authorize")
    public BaseResponse authorize(@Valid @RequestBody AuthorizationRequest request) {
        return atmService.authorize(request);
    }
}
