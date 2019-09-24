package com.jbp.couponproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbp.couponproject.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

}
