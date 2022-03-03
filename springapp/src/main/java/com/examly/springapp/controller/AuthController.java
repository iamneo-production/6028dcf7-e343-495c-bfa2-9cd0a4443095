package com.examly.springapp.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.model.AuthenticationRequest;
import com.examly.springapp.model.AuthenticationResponse;
import com.examly.springapp.security.jwt.JwtUtil;
import com.examly.springapp.service.UserService;

import com.examly.springapp.exception.UnverifiedUserException;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/user/signup")
	public ResponseEntity<?> addUser(@RequestBody User user, HttpServletRequest request){
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setUserRole("ROLE_USER");
		newUser.setEnabled(false);
		newUser.setPassword(passwordEncoder().encode(user.getPassword()));
		newUser.setMobileNumber(user.getMobileNumber());
		String k = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return new ResponseEntity(userService.saveUser(newUser, k), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/user/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
				);
		}catch(BadCredentialsException e) {
			throw new com.examly.springapp.exception.BadCredentialsException("Incorrect email or password");
		}catch (DisabledException e) {
			throw new UnverifiedUserException("User is not verified");
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		Collection<SimpleGrantedAuthority> k = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
//	@PostMapping("/user/login/password-reset")
//	public ResponseEntity<T>
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
