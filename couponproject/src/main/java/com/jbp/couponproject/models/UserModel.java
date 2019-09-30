package com.jbp.couponproject.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jbp.couponproject.enums.Roles;

/*
 * @This Model that is sent to the DB. 
 */

@Entity
public class UserModel {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Column
	private String name;
	@NotNull
	@Column
	private String username;
	@Column
	@NotNull
	@JsonIgnore
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Roles role;
	@ManyToMany
	@JsonIgnore
	private List<Coupon> coupons;

	public UserModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRoles() {
		return role;
	}

	public void setRoles(Roles roles) {
		this.role = roles;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void createCoupon(Coupon coupon) {
		if (this.coupons.contains(coupon)) {
			System.out.println("Already Exists");
		} else {
			this.coupons.add(coupon);
		}
	}

	public void removeCoupon(Coupon coupon) {
		if (this.coupons.contains(coupon)) {
			this.coupons.remove(coupon);
		}
	}

	public UserModel(@NotNull long id, @NotNull String username, @NotNull String name, @NotNull String password,
			@NotNull Roles roles) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.role = roles;

	}

}
