package com.energizeglobal.bank.aop;

import com.energizeglobal.bank.enums.EventState;
import com.energizeglobal.bank.models.atm.BaseAuthorizedRequest;
import com.energizeglobal.bank.models.atm.BaseUnauthorizedRequest;
import com.energizeglobal.bank.models.event.EventInfo;
import com.energizeglobal.bank.services.EventLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final EventLogService eventLogService;

    public LoggingAspect(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @Pointcut("within(com.energizeglobal.bank.services..*)")
    public void applicationPackagePointcut() {
    }

    @Around("applicationPackagePointcut() && @annotation(log)")
    public Object logAround(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        Object request = joinPoint.getArgs()[0];
        logger.info("{} Request: {}", log.eventType(), request);

        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventType(log.eventType());
        eventInfo.setEventState(EventState.Request);
        if (request instanceof BaseUnauthorizedRequest) {
            eventInfo.setCardNumber(((BaseUnauthorizedRequest) request).getCardNumber());
        }
        if (request instanceof BaseAuthorizedRequest) {
            eventInfo.setSessionId(((BaseAuthorizedRequest) request).getSessionId());
        }
        eventLogService.logEvent(eventInfo);


        Object result = joinPoint.proceed();


        eventInfo.setEventState(EventState.Success);
        eventLogService.logEvent(eventInfo);
        return result;
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && @annotation(log)", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Log log, Throwable ex) {
        Object request = joinPoint.getArgs()[0];
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventType(log.eventType());
        eventInfo.setEventState(EventState.Failure);
        eventInfo.setEventData(ex.getMessage());
        if (request instanceof BaseUnauthorizedRequest) {
            eventInfo.setCardNumber(((BaseUnauthorizedRequest) request).getCardNumber());
        }
        if (request instanceof BaseAuthorizedRequest) {
            eventInfo.setSessionId(((BaseAuthorizedRequest) request).getSessionId());
        }
        eventLogService.logEvent(eventInfo);
    }

}
