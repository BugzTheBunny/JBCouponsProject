package com.jbp.couponproject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.aspectj.weaver.ast.Var;
import org.hibernate.WrongClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.Coupon;
import com.jbp.couponproject.models.UserModel;
import com.jbp.couponproject.repos.CouponRepository;
import com.jbp.couponproject.repos.UserModelRepository;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	/*
	 * @ Returning users by roles
	 * 
	 * @need to add some if Statments
	 */

	@GetMapping("/{username}")
	public UserModel getUser(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}

	/*
	 * @Returns the current login user information.
	 */
	@GetMapping("/info")
	public UserModel myInfo(Authentication authentication) {
		return userRepository.findByUsername(authentication.getName());
	}

	@GetMapping("/list/customers")
	public List<UserModel> customerList() {
		List<UserModel> users = userRepository.findByRole(Roles.CUSTOMER);
		if (users.isEmpty()) {
			System.out.println("No Customers in the DB");
			return new ArrayList<UserModel>();
		}
		return users;

	}

	@GetMapping("/list/managers")
	public List<UserModel> managersList() {
		List<UserModel> users = userRepository.findByRole(Roles.MANAGER);
		if (users.isEmpty()) {
			System.out.println("No Managers in the DB");
			return new ArrayList<UserModel>();
		}
		return users;

	}

	@PutMapping(path = { "update" }, consumes = { "application/json" })
	public void updateUser(Authentication authentication, @RequestBody UserModel userModel) {
		// Comparing the username
		if (userRepository.findById(userModel.getId()).getUsername() == authentication.getName()) {
			UserModel tempUser = userRepository.findById(userModel.getId());
			userRepository.save(tempUser);
		} else {
			System.out.println("Not allowed to change other user info");
		}

	}
	/*
	 * A Method for changing a user password
	 * 
	 * 
	 * @ Will compare the users by username.
	 * 
	 * if both are equal, will change the password and encrypt it.
	 */

	@PutMapping(path = { "password" })
	public void updatePassword(Authentication authentication,@RequestBody String password) {
			UserModel tempUser = userRepository.findByUsername(authentication.getName());
			tempUser.setPassword(bcryptEncoder.encode(password));
			System.out.println("new password was encrypted");
			userRepository.save(tempUser);
	}

	/*
	 * Delete function, Only a user can delete himself (depends on the ID &
	 * username).
	 */

	@Transactional
	@DeleteMapping(path = { "/del/{id}" })
	public void deleteUser(Authentication authentication, @PathVariable("id") long id) {
		if (userRepository.findById(id).getUsername() == authentication.getName()) {
			/*
			 * If Manager:
			 */
			if (userRepository.findById(id).getRoles() == Roles.MANAGER) {
				UserModel tempUser = userRepository.findById(id);
				List<Coupon> tempCoupons = tempUser.getCoupons();
				for (Coupon coupon : tempCoupons) {
					couponRepository.deleteById(coupon.getId());
				}
				userRepository.delete(tempUser);
				/*
				 * If Customer:
				 */
			} else if (userRepository.findById(id).getRoles() == Roles.CUSTOMER) {
				UserModel tempUser = userRepository.findById(id);
				tempUser.setCoupons(null);
				userRepository.save(tempUser);
				userRepository.delete(tempUser);
				/*
				 * If Admin:
				 */
			} else if (userRepository.findById(id).getRoles() == Roles.ADMIN) {
				System.out.println("NOT FROM HERE.");
			}
		} else {
			System.err.println("Something went wrong...");
		}

	}

}
