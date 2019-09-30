package com.jbp.couponproject.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.repos.CouponRepository;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class DailyTask {

	/*
	 * @The daily task, removes the outdated components, also shows how much were
	 * removed every run.
	 *
	 */

	@Autowired
	private CouponRepository couponRepository;

	@Scheduled(initialDelay = 5000L, fixedDelay = 30000L)
	void update() {
		long amount = 0;
		LocalDate time = LocalDate.now();
		List<Coupon> outdated = couponRepository.findAllByEndDateBefore(Date.valueOf(LocalDate.now()));

		for (Coupon coupon : outdated) {
			System.err.println("Deleted coupon #" + coupon.getId());
			couponRepository.delete(coupon);
		}
		/*
		 * Info of the run for the console
		 */
		System.out.println("------- Number of updated coupons today: " + amount);
		System.out.println("Outdated Coupons: \n" + outdated);
		System.out.println("Daily task working " + time);
	}

}