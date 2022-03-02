package com.examly.springapp.service;

import com.examly.springapp.model.User;
import com.examly.springapp.model.VerificationToken;

public interface UserService {
	String saveUser(User user, String appURL);
	String editUser(Integer id, User user);
	Boolean checkUsernameExists(String username);
	Boolean checkEmailExists(String email);
	Boolean checkMobileNumberExists(String mobNo);
	void createVerificationToken(User user, String token);
	VerificationToken getVerificationToken(String token);
}
