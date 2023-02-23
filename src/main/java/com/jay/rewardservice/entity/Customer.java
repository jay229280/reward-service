package com.jay.rewardservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Transaction> transcations = new ArrayList<>();

}