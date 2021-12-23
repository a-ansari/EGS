package com.energizeglobal.bank.controllers;

import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.models.atm.DepositRequest;
import com.energizeglobal.bank.models.atm.WithdrawRequest;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.services.AtmService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @GetMapping("/balance")
    public BaseResponse balance(@RequestParam @NotNull String cardNumber,
                                @RequestParam @NotNull Long sessionId) {
        return atmService.balance(cardNumber, sessionId);
    }

    @PostMapping("/deposit")
    public BaseResponse deposit(@Valid @RequestBody DepositRequest request) {
        return atmService.deposit(request);
    }

    @PostMapping("/withdraw")
    public BaseResponse withdraw(@Valid @RequestBody WithdrawRequest request) {
        return atmService.withdraw(request);
    }

}
