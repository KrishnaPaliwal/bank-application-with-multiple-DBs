package com.example.bank.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.model.Customer;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
