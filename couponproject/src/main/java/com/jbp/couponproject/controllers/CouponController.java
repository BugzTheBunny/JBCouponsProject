package com.jbp.couponproject.controllers;

import java.sql.Date;
import java.time.LocalDate;
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

import com.jbp.couponproject.enums.CouponType;
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
	@DeleteMapping(path = { "/del/{id}" } )
	public void delete(@PathVariable("id") long id) {
		if (couponRepository.findById(id) != null) {
			couponRepository.deleteById(id);
		}
	}

	@PostMapping(consumes = { "application/json" })
	public void create(@RequestBody Coupon coupon) {
		System.out.println("DATE: " + coupon.getEndDate());
		if(coupon != null) {
			Coupon newCoupon = new Coupon();
			newCoupon.setId(coupon.getId());
			newCoupon.setTitle(coupon.getTitle());
			newCoupon.setDescription(coupon.getDescription());
			newCoupon.setAmount(coupon.getAmount());
			newCoupon.setStartDate(coupon.getStartDate());
			newCoupon.setEndDate(coupon.getEndDate());
			newCoupon.setPrice(coupon.getPrice());
			newCoupon.setType(CouponType.valueOf(coupon.getType().toString()));
		}
		couponRepository.save(coupon);
	}

}
