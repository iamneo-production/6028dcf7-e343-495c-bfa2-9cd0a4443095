package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.Optional;

import com.examly.springapp.service.UserService;
import com.examly.springapp.model.User;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {

    @Autowired
    UserService userService;


    @PostMapping("/user/signup")
    public ResponseEntity<?> addUser(@RequestBody User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole("ROLE_USER");
        newUser.setMobileNumber(user.getMobileNumber());

        userService.saveUser(newUser);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/home")
    public String ko(){
        return "what?";
    }
}
