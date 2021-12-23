package com.energizeglobal.bank.repositories;

import com.energizeglobal.bank.entities.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}
