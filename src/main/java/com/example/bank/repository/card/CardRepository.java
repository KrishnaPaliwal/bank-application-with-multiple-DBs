package com.example.bank.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.model.Card;

@Transactional
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}