package com.examly.springapp.service;

import javax.servlet.http.HttpServletRequest;

import com.examly.springapp.model.ReviewDTO;

public interface ReviewService {
	String processThemeReview(ReviewDTO dto, HttpServletRequest request);
}
