package com.jbp.couponproject.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;

	@Transactional
	@DeleteMapping("/deleteUser")
	public void removeUser(Authentication authentication, @RequestBody UserModel userModel) {
		System.out.println("Delete was called");
		// Will work only for Admins or Companies
		if (userRepository.findByUsername(authentication.getName()).getRoles().toString().equals("ADMIN")) {
			if (userModel != null) {
				userRepository.delete(userModel);
			}

		}

	}

	public void removeCoupon(Authentication authentication, @RequestBody UserModel userModel) {

	}
}
