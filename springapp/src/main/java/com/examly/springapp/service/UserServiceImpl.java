package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.examly.springapp.event.UserRegistrationEvent;
import com.examly.springapp.exception.DuplicateValueException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VerificationToken;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	VerificationTokenRepository tokenRepository;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Override
	public String saveUser(User user, String appURL) {

		if (checkEmailExists(user.getEmail())) {
			throw new DuplicateValueException("User", "email", user.getEmail());
		}
		if (checkUsernameExists(user.getUsername())) {
			throw new DuplicateValueException("User", "username", user.getUsername());
		}
		if (checkMobileNumberExists(user.getMobileNumber())) {
			throw new DuplicateValueException("User", "mobileNumber", user.getMobileNumber());
		}

		userRepository.save(user);
		eventPublisher.publishEvent(new UserRegistrationEvent(user, appURL));
		return "Verification link sent";
	}

	@Override
	public User displayUser(Integer id) {
		return userRepository.findById(id).orElseThrow(null);
	}

	@Override
	public String editUser(Integer id, User newUser) {
		userRepository.save(null);
		return "incomplete";
	}

// 	@Override
// 	public String editUser(Integer id, User newUser) {
// //		if(userRepository.findById(id)==null) {
// //			throw new ResourceNotFoundException("User not found with id: "+id);
// //		}
// //		userRepository.findById(id)
// //		.map(user->{
// //			user.setEmail(newUser.getEmail());
// //			user.setMobileNumber(newUser.getMobileNumber());
// //			if(newUser.getPassword()!=null) {
// //				user.setPassword(newUser.getPassword());
// //			}
// //		})
// 		return null;
// 	}

	@Override
	public Boolean checkUsernameExists(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean checkEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Boolean checkMobileNumberExists(String mobNo) {
		return userRepository.existsByMobileNumber(mobNo);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken vToken = new VerificationToken(token, user);
		tokenRepository.save(vToken);
	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}

	// for access verification in controller
	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User getUserByEmail(String email){
		return userRepository.findByEmail(email).orElse(null);
	}
}
