package com.examly.springapp.model;

import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="user")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	@Column(unique=true)
	private String username;
	
	@NotNull
	@Size(min=10,max=10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(unique=true)
	private String mobileNumber;
	
	@NotNull
	private String userRole;

    public User() {
    }
    
    public User(Integer userId, String email, String username, String password, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    
}
