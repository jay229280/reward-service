package com.jay.rewardservice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jay.rewardservice.dto.CustomerRewardDto;
import com.jay.rewardservice.entity.Customer;
import com.jay.rewardservice.entity.Transaction;
import com.jay.rewardservice.exception.BusinessException;
import com.jay.rewardservice.repository.CustomerRepository;
import com.jay.rewardservice.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private TransactionRepository transactionRepository;

	private Customer customer;

	private List<Transaction> firstMonthTransaction = new ArrayList<>();

	private List<Transaction> secondMonthTransaction = new ArrayList<>();

	private List<Transaction> thirdMonthTransaction = new ArrayList<>();

	@Before
	public void setup() {
		customer = new Customer(2253l, "Chid", null);

		LocalDate currentDate = LocalDate.now();

		Transaction transaction1 = new Transaction(2266l, 102.0, currentDate.minusDays(2), customer);
		Transaction transaction2 = new Transaction(2267l, 84.0, currentDate.minusDays(5), customer);
		Transaction transaction3 = new Transaction(2268l, 200.0, currentDate.minusMonths(1), customer);
		Transaction transaction4 = new Transaction(2269l, 103.0, currentDate.minusMonths(1), customer);
		Transaction transaction5 = new Transaction(2270l, 500.0, currentDate.minusMonths(2), customer);
		Transaction transaction6 = new Transaction(2271l, 585.0, currentDate.minusMonths(2), customer);
		Transaction transaction7 = new Transaction(2272l, 120.0, currentDate.minusMonths(5), customer);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction1);
		transactions.add(transaction2);
		transactions.add(transaction3);
		transactions.add(transaction4);
		transactions.add(transaction5);
		transactions.add(transaction6);
		transactions.add(transaction7);
		customer.setTranscations(transactions);

		firstMonthTransaction.add(transaction1);
		firstMonthTransaction.add(transaction2);

		secondMonthTransaction.add(transaction3);
		secondMonthTransaction.add(transaction4);

		thirdMonthTransaction.add(transaction5);
		thirdMonthTransaction.add(transaction6);
	}

	@Test
	public void testGetCustomers() {

		List<Customer> customers = new ArrayList<>();
		customers.add(customer);

		Mockito.when(customerRepository.findAll()).thenReturn(customers);

		List<Customer> actualCustomers = customerServiceImpl.getCustomers();
		assertThat(actualCustomers).isNotNull();
		assertEquals(actualCustomers.size(), 1);
		assertEquals(actualCustomers.get(0).getTranscations().size(), 7);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCustomerRewardByCustomerId_success() {

		Mockito.when(customerRepository.findByCustomerId(Mockito.anyLong())).thenReturn(customer);

		Mockito.when(transactionRepository.findAllByCustomerAndTransactionDateBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(firstMonthTransaction, secondMonthTransaction, thirdMonthTransaction);

		CustomerRewardDto customerRewardDto = customerServiceImpl.getCustomerRewardByCustomerId(2253l);

		assertThat(customerRewardDto).isNotNull();
		assertEquals(customerRewardDto.getFirstPreviosMonthReward(), Long.valueOf(88));
		assertEquals(customerRewardDto.getSecondPreviosMonthReward(), Long.valueOf(306));
		assertEquals(customerRewardDto.getThirdPreviosMonthReward(), Long.valueOf(1870));
		assertEquals(customerRewardDto.getTotalReward(), Long.valueOf(2264));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCustomerRewardByCustomerId_TransactionNotFound() {
		firstMonthTransaction = null;

		Mockito.when(customerRepository.findByCustomerId(Mockito.anyLong())).thenReturn(customer);

		Mockito.when(transactionRepository.findAllByCustomerAndTransactionDateBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(firstMonthTransaction, secondMonthTransaction, thirdMonthTransaction);

		CustomerRewardDto customerRewardDto = customerServiceImpl.getCustomerRewardByCustomerId(2253l);

		assertThat(customerRewardDto).isNotNull();
		assertEquals(customerRewardDto.getFirstPreviosMonthReward(), Long.valueOf(0));
		assertEquals(customerRewardDto.getSecondPreviosMonthReward(), Long.valueOf(306));
		assertEquals(customerRewardDto.getThirdPreviosMonthReward(), Long.valueOf(1870));
		assertEquals(customerRewardDto.getTotalReward(), Long.valueOf(2176));

	}

	@Test(expected = BusinessException.class)
	public void testGetCustomerRewardByCustomerId_InvalidCustomerId() {
		customerServiceImpl.getCustomerRewardByCustomerId(2253l);

	}

}