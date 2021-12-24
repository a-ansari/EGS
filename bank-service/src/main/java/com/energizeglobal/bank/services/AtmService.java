package com.energizeglobal.bank.services;

import com.energizeglobal.bank.aop.Log;
import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.entities.Session;
import com.energizeglobal.bank.enums.EventType;
import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.models.atm.BalanceRequest;
import com.energizeglobal.bank.models.atm.DepositRequest;
import com.energizeglobal.bank.models.atm.WithdrawRequest;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.models.response.SuccessfulResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AtmService {
    private final CardAuthorizationService cardAuthorizationService;
    private final CardOperationsService cardOperationsService;

    public AtmService(CardAuthorizationService cardAuthorizationService,
                      CardOperationsService cardOperationsService) {
        this.cardAuthorizationService = cardAuthorizationService;
        this.cardOperationsService = cardOperationsService;
    }

    @Log(eventType = EventType.Authorization)
    @Transactional
    public synchronized BaseResponse authorize(AuthorizationRequest request) {
        Card card = cardAuthorizationService.validateAuthRequest(request);
        Session session = cardAuthorizationService.createSession(card);
        return new SuccessfulResponse().addData("sessionId", session.getId());
    }


    @Log(eventType = EventType.Balance)
    @Transactional
    public synchronized BaseResponse balance(BalanceRequest request) {
        Card card = cardOperationsService.validate(request.getCardNumber(), request.getSessionId());
        Long balance = cardOperationsService.balance(card);
        return new SuccessfulResponse().addData("balance", balance);
    }

    @Log(eventType = EventType.Deposit)
    @Transactional
    public synchronized BaseResponse deposit(DepositRequest request) {
        Card card = cardOperationsService.validate(request.getCardNumber(), request.getSessionId());
        Long balance = cardOperationsService.deposit(card, request.getAmount());
        return new SuccessfulResponse().addData("balance", balance);
    }

    @Log(eventType = EventType.Withdraw)
    @Transactional
    public synchronized BaseResponse withdraw(WithdrawRequest request) {
        Card card = cardOperationsService.validate(request.getCardNumber(), request.getSessionId());
        Long balance = cardOperationsService.withdraw(card, request.getAmount());
        return new SuccessfulResponse().addData("balance", balance);
    }
}
