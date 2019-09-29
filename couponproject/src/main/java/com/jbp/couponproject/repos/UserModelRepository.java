package com.jbp.couponproject.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.models.UserModel;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByUsername(String username);

	boolean existsByUsername(String username);

	List<Coupon> findAllCouponsByUsername(String username);
}
