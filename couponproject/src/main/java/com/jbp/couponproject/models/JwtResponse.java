package com.jbp.couponproject.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String role;

	public JwtResponse(String jwttoken, String role) {
		this.role = role;
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getRole() {
		return this.role;
	}

}