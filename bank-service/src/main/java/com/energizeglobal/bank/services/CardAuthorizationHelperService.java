package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.enums.CardStatus;
import com.energizeglobal.bank.repositories.CardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardAuthorizationHelperService {
    private final int MAX_TRY_COUNT = 3;

    private final CardRepository cardRepository;

    public CardAuthorizationHelperService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void incrementTryCount(Card card) {
        int tryCount = card.getAuthCount() + 1;
        card.setAuthCount(tryCount);
        if (tryCount == MAX_TRY_COUNT) {
            card.setCardStatus(CardStatus.InvalidByWrongPin);
        }
        cardRepository.save(card);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void resetTryCount(Card card) {
        if (card.getAuthCount() != 0) {
            card.setAuthCount(0);
            cardRepository.save(card);
        }
    }

}
