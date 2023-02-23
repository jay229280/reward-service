package com.jay.rewardservice.service;

import java.util.List;

import com.jay.rewardservice.dto.CustomerRewardDto;
import com.jay.rewardservice.entity.Customer;

public interface CustomerService {

	/**
	 * Fetch all customer details.
	 * 
	 * @return List of {@link Customer}
	 */
	public List<Customer> getCustomers();

	/**
	 * Fetch the customer reward point for last three month transactions based on
	 * below parameter
	 * 
	 * A customer receives 2 points for every dollar spent over $100 in each
	 * transaction, plus 1 point for every dollar spent between $50 and $100 in each
	 * transaction.
	 * 
	 * @param customerId the customer Id
	 * @return {@link CustomerRewardDto}
	 */
	public CustomerRewardDto getCustomerRewardByCustomerId(Long customerId);

}