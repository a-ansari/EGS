package com.energizeglobal.bank.models.event;

import com.energizeglobal.bank.enums.EventState;
import com.energizeglobal.bank.enums.EventType;
import lombok.Data;

@Data
public class EventInfo {
    private EventType eventType;
    private EventState eventState;
    private String eventData;
    private String cardNumber;
    private Long sessionId;
}
