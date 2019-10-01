package com.jbp.couponproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;

@Entity
@Scope("singleton")
public class Profit  {

	@Id
	@NotNull
	private long walletID;
	@NotNull
	private double income;
	@NotNull
	
	private long transactions;
	public long getWalletID() {
		return walletID;
	}
	public void setWalletID(long walletID) {
		this.walletID = walletID;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public long getTransactions() {
		return transactions;
	}
	public void setTransactions(long transactions) {
		this.transactions = transactions;
	}
	
	
}
