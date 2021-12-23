package com.energizeglobal.bank.entities;

import com.energizeglobal.bank.enums.CardStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String pinCodeHash;

    private String fingerPrintHash;

    private Integer cvv2;

    private ZonedDateTime expirationDate;

    private Integer authCount;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @ManyToOne
    private Account account;

    /**************************************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinCodeHash() {
        return pinCodeHash;
    }

    public void setPinCodeHash(String pinCodeHash) {
        this.pinCodeHash = pinCodeHash;
    }

    public String getFingerPrintHash() {
        return fingerPrintHash;
    }

    public void setFingerPrintHash(String fingerPrintHash) {
        this.fingerPrintHash = fingerPrintHash;
    }

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAuthCount() {
        return authCount;
    }

    public void setAuthCount(Integer authCount) {
        this.authCount = authCount;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
