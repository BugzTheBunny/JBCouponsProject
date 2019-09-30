package com.jbp.couponproject.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.Coupon;
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

	/*
	 * 	User removing:
	 * @ Removing Customer - will delete what he bought
	 * @ Removing Manager - will delete what he created + owns
	 */
	
	@Transactional
	@DeleteMapping(path = { "/del/{id}" })
	public void delete(Authentication authentication, @PathVariable("id") long id) {
		System.out.println("Delete was called");
		if (userRepository.findByUsername(authentication.getName()).getRoles() == Roles.ADMIN) {
			if (userRepository.findById(id).getRoles() == Roles.MANAGER) {
				UserModel tempUser = userRepository.findById(id);
				List<Coupon> tempCoupons = tempUser.getCoupons();
				for (Coupon coupon : tempCoupons) {
					couponRepository.deleteById(coupon.getId());
				}
			}
			userRepository.deleteById(id);
		} else {
			System.err.println("Not an admin");
		}
	}

	public void removeCoupon(Authentication authentication, @RequestBody UserModel userModel) {

	}
}
