package com.jbp.couponproject.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbp.couponproject.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findById(long id);

	void deleteById(long id);
	
	List<Coupon> findAllByEndDateAfter(Date date);

	List<Coupon> findAllByEndDateBefore(Date date);


}
