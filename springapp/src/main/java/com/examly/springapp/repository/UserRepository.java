package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByMobileNumber(String mobileNumber);
	Optional<User> findByEmail(String email);
}
