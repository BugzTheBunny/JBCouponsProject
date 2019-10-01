package com.jbp.couponproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.UserDTO;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.UserModelRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserModel save(UserDTO user) {
		if (userRepository.existsByUsername(user.getUsername()) == false) {
			UserModel newUser = new UserModel();
			newUser.setUsername(user.getUsername());
			newUser.setName(user.getName());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			if (user.getRole().toString().equals("ADMIN")) {
				newUser.setRoles(Roles.ADMIN);
			} else if (user.getRole().toString().equals("MANAGER")) {
				newUser.setRoles(Roles.MANAGER);
			} else {
				newUser.setRoles(Roles.CUSTOMER);
			}
			return userRepository.save(newUser);
		} else {
			System.err.println("User already exists");
			return null;
		}
	}
}