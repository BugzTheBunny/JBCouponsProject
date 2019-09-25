package com.jbp.couponproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String title;
	@NotNull
	private double price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Coupon() {
		
	}
	
	public Coupon(long id, @NotNull String title, @NotNull double price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", price=" + price + "]";
	}
	
	

}
