package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	// @Size(min=8,max=30) size is the hashed password
	private String password;
	
	@NotNull
	@Size(min=5,max=30)
	@Column(unique=true)
	private String username;
	
	@NotNull
	@Size(min=10,max=10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(unique=true)
	private String mobileNumber;
	
	@NotNull
	private String userRole;

	@NotNull
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", nullable = false)
	private Set<Event> events = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private List<ThemeReview> reviews = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private List<AddOnReview> addOnReviews = new ArrayList<>();
	
	public User() {
		super();
	}

	public User(String email, String password, String username,
			String mobileNumber, String userRole) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.userRole = "ROLE_USER";
		this.enabled = false;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public List<ThemeReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<ThemeReview> reviews) {
		this.reviews = reviews;
	}

	public List<AddOnReview> getAddOnReviews() {
		return addOnReviews;
	}

	public void setAddOnReviews(List<AddOnReview> addOnReviews) {
		this.addOnReviews = addOnReviews;
	}
	
	
}
