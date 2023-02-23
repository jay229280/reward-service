package com.jay.rewardservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jay.rewardservice.dto.CustomerRewardDto;
import com.jay.rewardservice.entity.Customer;
import com.jay.rewardservice.entity.Transaction;
import com.jay.rewardservice.exception.BusinessException;
import com.jay.rewardservice.repository.CustomerRepository;
import com.jay.rewardservice.repository.TransactionRepository;
import com.jay.rewardservice.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private static final int REWARD_UPPER_LIMIT = 100;
	private static final int REWARD_LOWER_LIMIT = 50;

	private static final int REWARD_UPPER_LIMIT_AMOUNT = 2;
	private static final int REWARD_LOWER_LIMIT_AMOUNT = 1;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public CustomerRewardDto getCustomerRewardByCustomerId(Long customerId) {
		log.info("Fetch customer reward deatils for customer id  {}", customerId);
		Customer customer = customerRepository.findByCustomerId(customerId);

		if (Objects.isNull(customer)) {
			log.error("Invalid customer id {} no data found", customerId);
			throw new BusinessException("Invalid customer id no data found", "INVALID_CUSTOMER_ID",
					HttpStatus.BAD_REQUEST);
		}

		LocalDate currenDate = LocalDate.now();
		LocalDate firstPreviosMonth = currenDate.minusMonths(1);
		LocalDate secondPreviosMonth = currenDate.minusMonths(2);
		LocalDate thirdPreviosMonth = currenDate.minusMonths(3);

		List<Transaction> lastMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, firstPreviosMonth, currenDate);
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, secondPreviosMonth, firstPreviosMonth);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, thirdPreviosMonth, secondPreviosMonth);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		log.info("Last Month Reward Points amount {}", lastMonthRewardPoints);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		log.info("Second Month Reward Points amount {}", lastSecondMonthRewardPoints);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);
		log.info("Third Month Reward Points amount {}", lastThirdMonthRewardPoints);

		return new CustomerRewardDto(customer.getCustomerId(), customer.getCustomerName(), lastMonthRewardPoints,
				lastSecondMonthRewardPoints, lastThirdMonthRewardPoints,
				lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

	}

	private Long getRewardsPerMonth(List<Transaction> transactions) {

		if (Objects.isNull(transactions)) {
			return 0l;
		}
		return transactions.stream().map(transaction -> {

			if (transaction.getTransactionAmount() > REWARD_LOWER_LIMIT
					&& transaction.getTransactionAmount() <= REWARD_UPPER_LIMIT) {
				return Math.round(transaction.getTransactionAmount() - REWARD_LOWER_LIMIT) * REWARD_LOWER_LIMIT_AMOUNT;
			} else if (transaction.getTransactionAmount() > REWARD_UPPER_LIMIT) {
				return Math.round(transaction.getTransactionAmount() - REWARD_UPPER_LIMIT) * REWARD_UPPER_LIMIT_AMOUNT
						+ (REWARD_UPPER_LIMIT - REWARD_LOWER_LIMIT) * REWARD_LOWER_LIMIT_AMOUNT;
			} else {
				return 0l;
			}
		}).collect(Collectors.summingLong(Long::longValue));
	}

}