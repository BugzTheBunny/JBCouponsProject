package com.jbp.couponproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jbp.couponproject.config.JwtTokenUtil;
import com.jbp.couponproject.models.JwtRequest;
import com.jbp.couponproject.models.JwtResponse;
import com.jbp.couponproject.models.UserDTO;
import com.jbp.couponproject.repos.UserModelRepository;
import com.jbp.couponproject.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private String LOGGED_USER;

	public String getLOGGED_USER() {
		return LOGGED_USER;
	}

	private void setLOGGED_USER(String lOGGED_USER) {
		LOGGED_USER = lOGGED_USER;
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserModelRepository userModelRepository;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/*
	 * This method is used for the Login, checks the credentials and sets them.
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		/*
		 * sets the user Token + the Role.
		 */
		final String token = jwtTokenUtil.generateToken(userDetails);
		final String role = userModelRepository.findByUsername(authenticationRequest.getUsername()).getRoles()
				.toString();

		/*
		 * Returns the Token + the Role of the user.
		 */
		setLOGGED_USER(authenticationRequest.getUsername());
		return ResponseEntity.ok(new JwtResponse(token, role));
	}

	/*
	 * The post request for the registration, takes a user infetrface that includes
	 * Username, Password and the Role.
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		System.out.println("New account has been registerd" + user.toString());
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}