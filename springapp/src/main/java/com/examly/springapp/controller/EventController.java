package com.examly.springapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.AddOn;
import com.examly.springapp.model.BookEventRequest;
import com.examly.springapp.model.Event;
import com.examly.springapp.model.FoodItem;
import com.examly.springapp.model.Menu;
import com.examly.springapp.model.Theme;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.AddonRepository;
import com.examly.springapp.repository.EventRepository;
import com.examly.springapp.repository.FoodItemRepository;
import com.examly.springapp.repository.MenuRepository;
import com.examly.springapp.repository.ThemeRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.service.EventService;

@RestController
@PersistenceContext
public class EventController {

	@Autowired
	EventService eventService;

	// TEST
	@Autowired
	UserRepository userRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	FoodItemRepository foodItemRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	AddonRepository addonRepository;

	@PostMapping("/user/bookEvent")
	public ResponseEntity<?> bookEvent(@RequestBody BookEventRequest bookEventRequest, HttpServletRequest request) {
		return new ResponseEntity(eventService.bookEvent(bookEventRequest, request), HttpStatus.CREATED);
	}
	
	@PostMapping("/user/cancelEvent/{eventId}")
	public ResponseEntity<?> cancelEvent(@PathVariable int eventId, HttpServletRequest request) {
		System.out.println(eventId);
		return new ResponseEntity(eventService.cancelEvent(eventId, request), HttpStatus.CREATED);
	} 
}

