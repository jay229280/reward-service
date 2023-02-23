package com.jay.rewardservice.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;

	@Column(name = "TRANSACTION_AMOUNT")
	private Double transactionAmount;

	@Column(name = "TRANSACTION_DATE")
	private LocalDate transactionDate;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "CUSTOMER_ID")
	@JsonIgnore
	private Customer customer;

}