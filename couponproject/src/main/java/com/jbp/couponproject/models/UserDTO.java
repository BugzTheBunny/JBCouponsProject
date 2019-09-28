package com.jbp.couponproject.models;

import com.jbp.couponproject.enums.Roles;

public class UserDTO {
	
	private String username;
	private String password;
	private Roles role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	
	


	
}