package com.jbp.couponproject.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

import javassist.expr.NewArray;

@CrossOrigin
@RestController
@RequestMapping("/user")
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

	@Transactional
	@DeleteMapping(path = { "/del/{id}" })
	public void delete(Authentication authentication, @PathVariable("id") long id) {
		System.out.println("Delete was called");
		// Will work only for ADMIN
		if (userRepository.findByUsername(authentication.getName()).getRoles().toString().equals("ADMIN")) {
			if (userRepository.findById(id) != null) {
				UserModel temp = userRepository.findById(id);
				userRepository.findById(id).setCoupons(null);
				userRepository.save(temp);
				userRepository.delete(temp);
			}
		}
	}

}
