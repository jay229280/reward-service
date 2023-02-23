package com.jay.rewardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.rewardservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerId(Long customerId);

}