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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.models.Profit;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.ProfitRepo;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("coupon")
public class CouponController {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private ProfitRepo profitRepo;

	@GetMapping(path = "/{id}")
	public Coupon getCoupon(@PathVariable("id") long id) {
		return couponRepository.findById(1);
	}

	/*
	 * returns the current user coupons
	 */
	@GetMapping("/list")
	public List<Coupon> test(Authentication authentication) {
		List<Coupon> coupons = userRepository.findByUsername(authentication.getName()).getCoupons();
		return coupons;

	}

	@GetMapping("/all")
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

	@PostMapping(path = "", consumes = { "application/json" })
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

	@PostMapping(path = "/buy", consumes = { "application/json" })
	public void buy(Authentication authentication, @RequestBody Coupon coupon) {
		System.out.println("Purchuse was called by " + authentication.getName()
				+ userRepository.findByUsername(authentication.getName()).getRoles().toString());
		if (userRepository.findByUsername(authentication.getName()).getRoles().toString().equals("CUSTOMER")
				&& coupon.getAmount() >= 1) {
			UserModel temp = userRepository.findByUsername(authentication.getName());
			Coupon tempc = coupon;
			temp.createCoupon(tempc);
			tempc.setAmount(tempc.getAmount() - 1);
			couponRepository.save(tempc);
			userRepository.save(temp);
			/*
			 * Below is the profit managing
			 */
			Profit profit = profitRepo.findByWalletID(451);
			profit.setIncome(profit.getIncome()+coupon.getPrice());
			profit.setTransactions(profit.getTransactions()+1);
			profitRepo.save(profit);
			System.out.println("Buy coupon worked");
		}
	}

	@PutMapping(path = "/update", consumes = { "application/json" })
	public void update(Authentication authentication, @RequestBody Coupon coupon) {
		if (userRepository.findByUsername(authentication.getName()).getRoles().toString().equals("ADMIN")
				|| userRepository.findByUsername(authentication.getName()).getRoles().toString().equals("MANAGER")) {
			Coupon tempc = coupon;
			couponRepository.save(tempc);
			System.out.println("Update coupon worked");
		}
	}

}
