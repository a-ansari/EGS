package com.energizeglobal.bank.repositories;

import com.energizeglobal.bank.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
