package com.jay.rewardservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.rewardservice.dto.CustomerRewardDto;
import com.jay.rewardservice.entity.Customer;
import com.jay.rewardservice.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Rest Endpoint to fetch all customer and its associated transaction
	 * 
	 * @return List of {@link Customer}
	 */
	@GetMapping(value = "/list")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	/**
	 * Rest Endpoint to fetch customer reward based on their transaction for last
	 * three month duration based on customerId
	 * 
	 * @param customerId
	 * @return {@link CustomerRewardDto}
	 */
	@GetMapping(value = "/{customerId}/reward")
	public CustomerRewardDto getCustomerRewardByCustomerId(@PathVariable("customerId") Long customerId) {
		return customerService.getCustomerRewardByCustomerId(customerId);
	}

}