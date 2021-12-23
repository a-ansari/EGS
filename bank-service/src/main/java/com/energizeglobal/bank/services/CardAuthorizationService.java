package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.entities.Session;
import com.energizeglobal.bank.enums.CardStatus;
import com.energizeglobal.bank.exceptions.BankException;
import com.energizeglobal.bank.models.atm.AuthorizationRequest;
import com.energizeglobal.bank.repositories.CardRepository;
import com.energizeglobal.bank.repositories.SessionRepository;
import com.energizeglobal.bank.utils.Assert;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CardAuthorizationService {
    private final CardRepository cardRepository;
    private final SessionRepository sessionRepository;
    private final CardAuthorizationHelperService cardAuthorizationHelperService;

    public CardAuthorizationService(CardRepository cardRepository, SessionRepository sessionRepository,
                                    CardAuthorizationHelperService cardAuthorizationHelperService) {
        this.cardRepository = cardRepository;
        this.sessionRepository = sessionRepository;
        this.cardAuthorizationHelperService = cardAuthorizationHelperService;
    }

    public Card validateAuthRequest(AuthorizationRequest request) {
        Card card = cardRepository.getCardByCardNumber(request.getCardNumber());

        Assert.notNull(card, "Card Not Found");

        Assert.isEquals(card.getCardStatus(), CardStatus.Valid, "Card Status is " + card.getCardStatus().name());

        String hashCode = null;
        switch (request.getAuthMethod()) {
            case PinCode:
                hashCode = card.getPinCodeHash();
                break;
            case Fingerprint:
                hashCode = card.getFingerPrintHash();
                break;
        }
        try {
            Assert.isEquals(hashCode, request.getHashCode(), "Hash Code is not correct");
        } catch (BankException ex) {
            cardAuthorizationHelperService.incrementTryCount(card);
            throw ex;
        }

        cardAuthorizationHelperService.resetTryCount(card);

        return card;
    }

    public Session createSession(Card card) {
        Session session = new Session();
        session.setCard(card);
        session.setCreationTime(ZonedDateTime.now());
        sessionRepository.save(session);
        return session;
    }
}
