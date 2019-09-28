package com.jbp.couponproject.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("/coupon")
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
	public List<Coupon> getCoupons() {
		return couponRepository.findAll();

	}

	@Transactional
	@DeleteMapping(path = { "/del/{id}" })
	public void delete(@PathVariable("id") long id) {
		if (couponRepository.findById(id) != null) {
			couponRepository.deleteById(id);
		}
	}

	@PostMapping
	public void create(@RequestBody Coupon coupon) {
		couponRepository.save(coupon);
	}

}
