package com.example.bank.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.model.Account;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}