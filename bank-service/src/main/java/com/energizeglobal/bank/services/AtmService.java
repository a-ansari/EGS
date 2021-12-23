package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.entities.Session;
import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.models.response.BaseResponse;
import com.energizeglobal.bank.models.response.SuccessfulResponse;
import com.energizeglobal.bank.repositories.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AtmService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CardRepository cardRepository;
    private final CardAuthorizationService cardAuthorizationService;

    public AtmService(CardRepository cardRepository,
                      CardAuthorizationService cardAuthorizationService) {
        this.cardRepository = cardRepository;
        this.cardAuthorizationService = cardAuthorizationService;
    }

    public synchronized BaseResponse authorize(AuthorizationRequest request) {
        logger.info("Authorization Request: {}", request);

        Card card = cardAuthorizationService.validateAuthRequest(request);
        Session session = cardAuthorizationService.createSession(card);

        return new SuccessfulResponse().addData("sessionId", session.getId());
    }

}
