package com.examly.springapp.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.VerificationTokenRepository;
import com.examly.springapp.model.VerificationToken;
import com.examly.springapp.model.AuthenticationRequest;
import com.examly.springapp.model.AuthenticationResponse;
import com.examly.springapp.security.jwt.JwtUtil;
import com.examly.springapp.service.UserService;

import com.examly.springapp.event.PasswordResetEvent;
import com.examly.springapp.exception.UnverifiedUserException;
import com.examly.springapp.exception.BadVerificationTokenException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.PasswordResetRequest;

@CrossOrigin
@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@PostMapping("/user/signup")
	public ResponseEntity<?> addUser(@RequestBody User user, HttpServletRequest request){
		user.setUserRole("ROLE_USER");
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return new ResponseEntity(userService.saveUser(user, baseUrl), HttpStatus.CREATED);
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
		// Collection<SimpleGrantedAuthority> k = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/user/verify-registration")
	public ResponseEntity<?> verifyRegistration(@RequestParam String token) {
		
		VerificationToken verificationToken = userService.getVerificationToken(token);
		
		if(verificationToken==null) {
			throw new BadVerificationTokenException("Invalid Token");
		}
		
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			throw new BadVerificationTokenException("Token Expired");
		}
		user.setEnabled(true);
		userRepository.save(user);
		verificationToken.setExpiryDate(new Date(cal.getTime().getTime()));
		tokenRepository.save(verificationToken);
		return new ResponseEntity("Account activated", HttpStatus.OK);
	}

	@PostMapping("/user/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest prReq, HttpServletRequest request){
		String email = prReq.getEmail();
		User user = userService.getUserByEmail(email);
		if(user==null){
			throw new ResourceNotFoundException("User not found");
		}
		String token = UUID.randomUUID().toString();
		// TODO: delete previous tokens for user
		userService.createVerificationToken(user, token);
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		eventPublisher.publishEvent(new PasswordResetEvent(user, baseUrl));
		return new ResponseEntity("Password reset link sent", HttpStatus.OK);
	}
	
//	@PostMapping("/user/login/password-reset")
//	public ResponseEntity<T>
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
