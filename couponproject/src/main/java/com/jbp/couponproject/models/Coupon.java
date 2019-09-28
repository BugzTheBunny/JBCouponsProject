package com.jbp.couponproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jbp.couponproject.enums.CouponType;

@Entity
public class Coupon {

	/*
	 * @long id
	 * 
	 * @String title
	 * 
	 * @String description
	 * 
	 * @double price
	 * 
	 * @int amount
	 * 
	 * @Date - endDate
	 * 
	 * @Date - startDate (default current time)
	 * 
	 * @Ctype - couponType
	 * 
	 */

	@Id
	@GeneratedValue
	@Column
	private long id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private double price;
	@NotNull
	private int amount;
	@Temporal(TemporalType.DATE)
	@Column(name = "startDate")
	private Date startDate = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name = "endDate")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column
	private CouponType type;

	public Coupon() {

	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String message) {
		this.description = message;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}
	
	


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Coupon(long id, @NotNull String title, @NotNull String description, @NotNull double price,
			@NotNull int amount, Date startDate, Date endDate, CouponType type) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", message=" + description + ", price=" + price + ", amount="
				+ amount + ", start_date=" + startDate + ", end_date=" + endDate + ", type=" + type + "]";
	}

}
