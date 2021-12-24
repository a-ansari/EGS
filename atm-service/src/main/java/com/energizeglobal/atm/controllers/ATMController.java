package com.energizeglobal.atm.controllers;

import com.energizeglobal.atm.controllers.models.requests.AuthorizationRequest;
import com.energizeglobal.atm.controllers.models.requests.BalanceRequest;
import com.energizeglobal.atm.controllers.models.requests.DepositRequest;
import com.energizeglobal.atm.controllers.models.requests.WithdrawRequest;
import com.energizeglobal.atm.controllers.models.responses.BaseResponse;
import com.energizeglobal.atm.services.ATMService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/atm")
public class ATMController {
    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/authorize")
    public BaseResponse authorize(@Valid @RequestBody AuthorizationRequest request) {
        return atmService.authorize(request);
    }

    @GetMapping("/balance")
    public BaseResponse balance(@RequestParam @NotNull String cardNumber,
                                @RequestParam @NotNull Long sessionId) {
        BalanceRequest request = new BalanceRequest();
        request.setCardNumber(cardNumber);
        request.setSessionId(sessionId);
        return atmService.balance(request);
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
