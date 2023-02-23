package com.jay.rewardservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.rewardservice.entity.Customer;
import com.jay.rewardservice.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByCustomerAndTransactionDateBetween(Customer customer, LocalDate start, LocalDate end);

}