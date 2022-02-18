package com.examly.springapp.service;

import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

}