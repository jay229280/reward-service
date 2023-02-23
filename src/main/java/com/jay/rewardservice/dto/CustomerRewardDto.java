package com.jay.rewardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewardDto {

	private Long customerId;
	private String customerName;
	private Long firstPreviosMonthReward;
	private Long secondPreviosMonthReward;
	private Long thirdPreviosMonthReward;
	private Long totalReward;

}