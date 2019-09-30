package com.jbp.couponproject.models;

import com.jbp.couponproject.enums.Roles;

/*
 * This class is used to connect the UserModel with the Jwt controller.
 */
public class UserDTO {

	private String username;
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}