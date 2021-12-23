package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.entities.Session;
import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.models.atm.DepositRequest;
import com.energizeglobal.bank.models.atm.WithdrawRequest;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.models.response.SuccessfulResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AtmService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CardAuthorizationService cardAuthorizationService;
    private final CardOperationsService cardOperationsService;

    public AtmService(CardAuthorizationService cardAuthorizationService,
                      CardOperationsService cardOperationsService) {
        this.cardAuthorizationService = cardAuthorizationService;
        this.cardOperationsService = cardOperationsService;
    }

    @Transactional
    public synchronized BaseResponse authorize(AuthorizationRequest request) {
        logger.info("Authorization Request: {}", request);

        Card card = cardAuthorizationService.validateAuthRequest(request);
        Session session = cardAuthorizationService.createSession(card);

        return new SuccessfulResponse().addData("sessionId", session.getId());
    }

    @Transactional
    public synchronized BaseResponse balance(String cardNumber, Long sessionId) {
        logger.info("Balance Request: {}, {}", cardNumber, sessionId);

        Card card = cardOperationsService.validate(cardNumber, sessionId);
        Long balance = cardOperationsService.balance(card);

        return new SuccessfulResponse().addData("balance", balance);
    }

    @Transactional
    public synchronized BaseResponse deposit(DepositRequest request) {
        logger.info("Deposit Request: {}", request);

        Card card = cardOperationsService.validate(request.getCardNumber(), request.getSessionId());
        Long balance = cardOperationsService.deposit(card, request.getAmount());

        return new SuccessfulResponse().addData("balance", balance);
    }

    @Transactional
    public synchronized BaseResponse withdraw(WithdrawRequest request) {
        logger.info("Withdraw Request: {}", request);

        Card card = cardOperationsService.validate(request.getCardNumber(), request.getSessionId());
        Long balance = cardOperationsService.withdraw(card, request.getAmount());

        return new SuccessfulResponse().addData("balance", balance);
    }
}
