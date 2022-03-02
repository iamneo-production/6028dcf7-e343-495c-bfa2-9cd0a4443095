package com.examly.springapp.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	private String mobileNumber;
	private String userRole;
	private Boolean isEnabled;
	
	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.mobileNumber = user.getMobileNumber();
		this.userRole = user.getUserRole();
		this.isEnabled = user.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(userRole));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// for this project
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// for this project
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// for this project
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

}
