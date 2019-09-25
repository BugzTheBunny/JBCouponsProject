package com.jbp.couponproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Column(unique = true)
	private String name;
	@NotNull
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	public User() {

	}

	public User(@NotNull long id, @NotNull String name, @NotNull String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

}
