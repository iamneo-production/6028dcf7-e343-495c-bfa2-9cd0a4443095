package com.examly.springapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.exception.UnauthorizedAccessException;
import com.examly.springapp.model.Event;
import com.examly.springapp.model.ReviewDTO;
import com.examly.springapp.model.Theme;
import com.examly.springapp.model.ThemeReview;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.ThemeReviewRepository;


@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	EventService eventService;
	
	@Autowired
	ThemeService themeService;
	
	@Autowired
	UserService  userService;
	
	@Autowired
	ThemeReviewRepository themeReviewRepository;
	
	
	@Override
	public String processThemeReview(ReviewDTO dto, HttpServletRequest request) {
		Event event = eventService.getEventById(dto.getEventId());
		if(event==null) {
			throw new ResourceNotFoundException("Event not found");
		}
		
		Theme theme = themeService.getThemeById(event.getEventTheme().getThemeId());
		if(theme==null) {
			throw new ResourceNotFoundException("Theme not found");
		}
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByEmail(userDetails.getUsername());
		if(user==null) {
			throw new UnauthorizedAccessException("Forbidden");
		}
		
		List<ThemeReview> reviews = user.getReviews();
		reviews.add(dto.getReview());
		
		List<ThemeReview> reviews2 = theme.getReviews();
		reviews2.add(dto.getReview());
		
		themeReviewRepository.save(dto.getReview());
		return "review added";
	}


}












