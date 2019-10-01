package com.jbp.couponproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbp.couponproject.models.Profit;

public interface ProfitRepo extends JpaRepository<Profit, Long>{
	
	Profit findByWalletID(long walletID);

}