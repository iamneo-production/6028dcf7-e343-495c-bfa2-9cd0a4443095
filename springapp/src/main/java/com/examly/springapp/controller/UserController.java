package com.examly.springapp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String verifyRegistration(@RequestParam String token) {
		
		VerificationToken verificationToken = userService.getVerificationToken(token);
		
		if(verificationToken==null) {
			return "Invalid Token";
		}
		
		User user = verificationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		
		return "account active";
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@RequestBody User user, @PathVariable Integer id) {
//		return new ResponseEntity(userService.editUser(id,user));
		return null;
	}
}
