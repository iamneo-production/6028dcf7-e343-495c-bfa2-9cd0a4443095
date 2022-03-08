package com.examly.springapp.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.*;
import com.examly.springapp.model.User;
import com.examly.springapp.model.MyUserDetails;
import com.examly.springapp.model.VerificationToken;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.VerificationTokenRepository;
import com.examly.springapp.service.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/user/test")
	public String test() {

		Collection<SimpleGrantedAuthority> k = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(k);
		return "OK";
	}

	@GetMapping("/user/viewUser/{id}")
	public ResponseEntity<?> displayUser(@PathVariable Integer id){
		User user = userService.displayUser(id);				

		if(user!=null ) {
			// admin has access to all user details
			if(isRequestUserAdmin()) {
				return new ResponseEntity(user, HttpStatus.OK);	
			}
			// user has access only to self details
			user.setPassword("****");
			if(isRequestUserAccessUser(id)) // user performing operations on itself
				return new ResponseEntity(user, HttpStatus.OK);
			else
				throw new UnauthorizedAccessException("Permission Denied");
		}
		throw new ResourceNotFoundException("User not found");
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@RequestBody User user, @PathVariable Integer id) {
		User user2 = userService.getUserById(id);
		user2.setEmail(user.getEmail());
		user.setMobileNumber(user.getMobileNumber());
		if(userService.checkUsernameExists(user.getUsername())) throw new DuplicateValueException("User", "username", user.getUsername());
		user.setUsername(user.getUsername());
		if(user2==null) {
			throw new ResourceNotFoundException("User not found");
		}
		if(isRequestUserAdmin()) {
			return new ResponseEntity(userService.editUser(id, user), HttpStatus.CREATED);
		}
		if(isRequestUserAccessUser(id)) {
			return new ResponseEntity(userService.editUser(id, user), HttpStatus.CREATED);			
		}
		throw new UnauthorizedAccessException("Permission Denied");
	}
	
	private boolean isRequestUserAccessUser(Integer id){
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserById(id);
		if(currentUser.getUsername().equals(user.getEmail()))
			return true;
		return false;
	}
	
	private boolean isRequestUserAdmin() {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return currentUser.getAuthorities().stream().anyMatch(gA->gA.getAuthority().equals("ROLE_ADMIN"));
	}
}
