package com.jbp.couponproject.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("coupon")
public class CouponController {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;

	@GetMapping(path = "/{id}")
	public Coupon getCoupon(@PathVariable("id") long id) {
		return couponRepository.findById(1);
	}

	@GetMapping("/list")
	public List<Coupon> test(Authentication authentication) {
		List<Coupon> coupons = userRepository.findByUsername(authentication.getName()).getCoupons();
		return coupons;

	}

	@GetMapping("/store")
	public List<Coupon> getCoupons() {
		return couponRepository.findAll();

	}

	/*
	 * @ Delets coupon by the ID of the Manager
	 * 
	 * @ Works only for Managers
	 * 
	 * @ Deletes by id for the current logged user.
	 */
	@Transactional
	@DeleteMapping(path = { "/del/{id}" })
	public void delete(Authentication authentication, @PathVariable("id") long id) {
		System.out.println("Delete was called");
		// Will work only for Admins or Companies
		if (userRepository.findByUsername(authentication.getName()).getRoles().equals("MANAGER")
				|| userRepository.findByUsername(authentication.getName()).getRoles().equals("ADMIN")) {
			if (couponRepository.findById(id) != null) {
				UserModel temp = userRepository.findByUsername(authentication.getName());
				temp.removeCoupon(couponRepository.findById(id));
				couponRepository.deleteById(id);
				userRepository.save(temp);
			}
		}
	}

	/*
	 * @ creates a coupon for the current logged user (Only for Admins / Managers)
	 */
	@PostMapping(consumes = { "application/json" })
	public void create(Authentication authentication, @RequestBody Coupon coupon) {
		System.out.println("Create was called");
		if (coupon != null) {
			// updating UserModel_Coupon
			UserModel temp = userRepository.findByUsername(authentication.getName());
			couponRepository.save(coupon);
			temp.createCoupon(coupon);
			userRepository.save(temp);
		}
	}

	@PostMapping(path = "/buy")
	public void buy(Authentication authentication, @RequestBody Coupon coupon) {
		System.out.println("Purchuse was called");
		if (userRepository.findByUsername(authentication.getName()).getRoles().equals("CUSTOMER")
				&& coupon.getAmount() >= 1) {
			UserModel temp = userRepository.findByUsername(authentication.getName());
			Coupon tempc = coupon;
			temp.createCoupon(tempc);
			tempc.setAmount(tempc.getAmount() - 1);
			couponRepository.save(tempc);
			userRepository.save(temp);
			System.out.println("Everything went fine");
		}
	}

}