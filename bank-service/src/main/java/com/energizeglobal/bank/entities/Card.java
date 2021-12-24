package com.energizeglobal.bank.entities;

import com.energizeglobal.bank.enums.CardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "card")
@Getter
@Setter
@ToString
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
}
