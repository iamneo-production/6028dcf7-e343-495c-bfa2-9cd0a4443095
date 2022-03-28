package com.examly.springapp.service;

import javax.servlet.http.HttpServletRequest;

import com.examly.springapp.model.BookEventRequest;
import com.examly.springapp.model.Event;

public interface EventService {

	Event getEventById(int id);
	String bookEvent(BookEventRequest bookEventRequest, HttpServletRequest request);
	String cancelEvent(int eventId,HttpServletRequest request);
}
