package com.jbp.couponproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;

	/*
	 * @ Returning users by roles
	 * 
	 * @need to add some if Statments
	 */

	@GetMapping("/list/customers")
	public List<UserModel> customerList() {
		List<UserModel> users = userRepository.findByRole(Roles.CUSTOMER.toString());
		return users;

	}

	@GetMapping("/list/managers")
	public List<UserModel> managersList() {
		List<UserModel> users = userRepository.findByRole(Roles.MANAGER.toString());
		return users;

	}

}
