package com.examly.springapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ReviewDTO;
import com.examly.springapp.service.ReviewService;

@RestController
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/user/postThemeReview")
	public String postThemeReview(@RequestBody ReviewDTO dto, HttpServletRequest request) {
		return reviewService.processThemeReview(dto, request);
	}
}


