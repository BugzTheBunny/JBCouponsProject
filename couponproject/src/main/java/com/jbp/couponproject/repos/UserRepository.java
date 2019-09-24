package com.jbp.couponproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbp.couponproject.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
