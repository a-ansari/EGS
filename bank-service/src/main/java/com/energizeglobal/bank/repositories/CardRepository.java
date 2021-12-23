package com.energizeglobal.bank.repositories;

import com.energizeglobal.bank.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
