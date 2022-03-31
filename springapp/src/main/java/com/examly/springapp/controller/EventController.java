package com.examly.springapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.BookEventRequest;
import com.examly.springapp.service.EventService;

@RestController
public class EventController {

	@Autowired
	EventService eventService;


	@PostMapping("/user/bookEvent")
	public ResponseEntity<?> bookEvent(@RequestBody BookEventRequest bookEventRequest, HttpServletRequest request) {
		return new ResponseEntity(eventService.bookEvent(bookEventRequest, request), HttpStatus.CREATED);
	}
	
	@PostMapping("/user/cancelEvent/{eventId}")
	public ResponseEntity<?> cancelEvent(@PathVariable int eventId, HttpServletRequest request) {
		return new ResponseEntity(eventService.cancelEvent(eventId, request), HttpStatus.OK);
	} 

	@GetMapping("/user/viewBookedEvents")
	public ResponseEntity<?> cancelEvent(HttpServletRequest request) {
		return new ResponseEntity(eventService.viewBookedEvents(request), HttpStatus.OK);
	} 
}

