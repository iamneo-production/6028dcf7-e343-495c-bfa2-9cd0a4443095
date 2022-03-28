package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

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

@Service
@PersistenceContext
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserService userService;

	@Autowired
	ThemeRepository themeRepository;

	@Autowired
	FoodItemRepository foodItemRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	AddonRepository addonRepository;
	
	@Override
	public Event getEventById(int id) {
		return eventRepository.findById(id).get();
	}

	@Override
	public String bookEvent(BookEventRequest bookEventRequest, HttpServletRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserByEmail(username);

		// 1. Create Event
		Event event = new Event();
		event.setApplicantName(bookEventRequest.getApplicantName());
		event.setEventName(bookEventRequest.getEventName());
		event.setEventAddress(bookEventRequest.getEventAddress());
		event.setEventDate(bookEventRequest.getEventDate());
		event.setApplicantAddress(bookEventRequest.getApplicantAddress());
		event.setApplicantEmail(user.getEmail());
		event.setApplicantMobile(user.getMobileNumber());
		// event.set

		// 2. Map Theme
		Theme theme = themeRepository.findById(bookEventRequest.getThemeId()).get();

		// 3. Fetch items
		Set<FoodItem> foodItems = new HashSet<>();
		for(int i : bookEventRequest.getFoodItemsId()){
			foodItems.add(foodItemRepository.findById(i).get());
		}

		// 3. Create Food Menu
		Menu menu = new Menu();
		menu.setFoodMenuItems(foodItems);
		menu.getFoodMenuCost();
		menu.getFoodMenuType();
		menuRepository.save(menu);

		// 4. Map Addons
		Set<AddOn> addOns = new HashSet<>();
		for (int k : bookEventRequest.getAddOnsId()) {
			addOns.add(addonRepository.findById(k).get());
		}

		event.setEventTheme(theme);
		event.setEventMenu(menu);
		event.setEventAddonsId(addOns);
		user.getEvents().add(event);

		eventRepository.save(event);

		// TODO: Send mail
		return "Event Booked";
	}

}
