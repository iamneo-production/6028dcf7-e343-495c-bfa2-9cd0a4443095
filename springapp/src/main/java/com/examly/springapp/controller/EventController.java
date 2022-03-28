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
	
	@PostMapping("/dev/sel-menu")
	public void selmenu(@RequestBody Set<Integer> ids){
		Set<FoodItem> fis = new HashSet<>();
		for(int id:ids) {
			fis.add(foodItemRepository.findById(id).get());
		}
		
		Menu mk = new Menu();
		mk.setFoodMenuItems(fis);
		mk.getFoodMenuCost();
		mk.getFoodMenuType();
		
		menuRepository.save(mk);
		
	}
	
	@PostMapping("/dev/sel-addons")
	@Transactional
	public void seladdons(@RequestBody Set<Integer> ids) {
		Set<AddOn> as = new HashSet<>();
		for(int id:ids) {
			as.add(addonRepository.findById(id).get());
		}
		
		Event ev = new Event("ename1");
		ev.setEventAddonsId(as);
		ev.setEventMenu(menuRepository.findById(1).get());
		ev.setEventTheme(themeRepository.findById(1).get());
		
		eventRepository.save(ev);
	}
}


// String principal = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		// User user = userRepository.findByEmail(principal).get();
		// System.out.println(principal);

		// Event event = new Event();
		// event.setEventName(bookEventRequest.getEventName());
		// event.setEventAddress(bookEventRequest.getEventAddress());
		// event.setEventDate(bookEventRequest.getEventDate());
		// event.setApplicantAddress(bookEventRequest.getApplicantAddress());

		// Theme theme = themeRepository.findById(bookEventRequest.getThemeId()).get();

		// Set<FoodItem> foodItems = new HashSet<>();
		// for (int k : bookEventRequest.getFoodItemsId()) {
		// 	foodItems.add(foodItemRepository.findById(k).get());
		// }

		// System.out.println(foodItems);
		// System.out.println(bookEventRequest.getFoodItemsId());
		// Menu menu = new Menu();

		// menu.setFoodMenuItems(foodItems);
		// menu.getFoodMenuCost();
		// menu.getFoodMenuType();

		// menuRepository.save(menu);

		// Set<AddOn> addOns = new HashSet<>();
		// for (int k : bookEventRequest.getAddOnsId()) {
		// 	addOns.add(addonRepository.findById(k).get());
		// }

		// event.setEventTheme(theme);
		// event.setEventMenu(menu);
		// event.setEventAddonsId(addOns);
		// user.getEvents().add(event);

		// eventRepository.save(event);

		// return "k";