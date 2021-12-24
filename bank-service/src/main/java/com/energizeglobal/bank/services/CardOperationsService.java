package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.Account;
import com.energizeglobal.bank.entities.Card;
import com.energizeglobal.bank.entities.Session;
import com.energizeglobal.bank.repositories.AccountRepository;
import com.energizeglobal.bank.repositories.SessionRepository;
import com.energizeglobal.bank.utils.Assert;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CardOperationsService {
    public static final long MAX_SESSION_TIME = 600;

    private final SessionRepository sessionRepository;
    private final AccountRepository accountRepository;

    public CardOperationsService(SessionRepository sessionRepository,
                                 AccountRepository accountRepository) {
        this.sessionRepository = sessionRepository;
        this.accountRepository = accountRepository;
    }

    public Card validate(String cardNumber, Long sessionId) {
        Session session = sessionRepository.getById(sessionId);
        Card card = session.getCard();
        Assert.isEquals(card.getCardNumber(), cardNumber, "Card Number is different than session");

        long elapsedTime = ZonedDateTime.now().toEpochSecond() - session.getCreationTime().toEpochSecond();
        Assert.isTrue(elapsedTime < MAX_SESSION_TIME, "Session has been expired");

        return card;
    }

    public Long balance(Card card) {
        return card.getAccount().getBalance();
    }

    public Long deposit(Card card, Long amount) {
        Account account = card.getAccount();
        long newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        return account.getBalance();
    }

    public Long withdraw(Card card, Long amount) {
        Account account = card.getAccount();
        long newBalance = account.getBalance() - amount;
        Assert.isTrue(newBalance >= 0, "Insufficient Account Balance");
        account.setBalance(newBalance);
        accountRepository.save(account);
        return account.getBalance();
    }
}
