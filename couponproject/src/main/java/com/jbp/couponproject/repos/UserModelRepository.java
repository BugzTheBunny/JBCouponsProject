package com.jbp.couponproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbp.couponproject.models.UserModel;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

}
