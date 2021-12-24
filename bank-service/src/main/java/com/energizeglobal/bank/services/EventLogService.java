package com.energizeglobal.bank.services;

import com.energizeglobal.bank.entities.EventLog;
import com.energizeglobal.bank.models.event.EventInfo;
import com.energizeglobal.bank.repositories.EventLogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
public class EventLogService {
    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void logEvent(EventInfo eventInfo) {
        EventLog eventLog = new EventLog();
        eventLog.setEventTime(ZonedDateTime.now());
        eventLog.setEventType(eventInfo.getEventType());
        eventLog.setEventState(eventInfo.getEventState());
        eventLog.setEventData(eventInfo.getEventData());
        eventLog.setCardNumber(eventInfo.getCardNumber());
        eventLog.setSessionId(eventInfo.getSessionId());
        eventLogRepository.save(eventLog);
    }
}
