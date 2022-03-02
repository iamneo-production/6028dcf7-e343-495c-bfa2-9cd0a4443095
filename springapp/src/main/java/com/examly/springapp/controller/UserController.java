package com.examly.springapp.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.BadVerificationTokenException;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VerificationToken;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.VerificationTokenRepository;
import com.examly.springapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@GetMapping("/user/test")
	public String test() {

		Collection<SimpleGrantedAuthority> k = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(k);
		return "OK";
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
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@RequestBody User user, @PathVariable Integer id) {
//		return new ResponseEntity(userService.editUser(id,user));
		return null;
	}
}
