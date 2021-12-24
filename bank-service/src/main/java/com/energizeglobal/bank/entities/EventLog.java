package com.energizeglobal.bank.entities;

import com.energizeglobal.bank.enums.EventState;
import com.energizeglobal.bank.enums.EventType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "event_log")
@Getter
@Setter
@ToString
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventState eventState;

    private ZonedDateTime eventTime;

    private String eventData;

    private String cardNumber;

    private Long sessionId;
}
