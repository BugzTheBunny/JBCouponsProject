package com.jbp.couponproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.jbp.couponproject.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findById(long id);

	void deleteById(long id);

}
