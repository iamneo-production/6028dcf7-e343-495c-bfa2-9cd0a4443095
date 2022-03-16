package com.examly.springapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.FoodItem;
import com.examly.springapp.model.Menu;
import com.examly.springapp.repository.FoodItemRepository;
import com.examly.springapp.repository.MenuRepository;
import com.examly.springapp.service.FoodItemService;

@RestController
public class FoodItemController {
	@Autowired
	FoodItemService foodItemService;
	
	@PostMapping("/admin/addMenu")
	public ResponseEntity<?> addFoodItem(@RequestBody FoodItem foodItem){
		return new ResponseEntity(foodItemService.addFoodItem(foodItem), HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/getMenu")
	public ResponseEntity<?> getFoodItems(){
		return new ResponseEntity(foodItemService.getFoodItems(), HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/deleteMenu/{id}")
	public ResponseEntity<?> deleteFoodItem(@PathVariable String id){
		return new ResponseEntity(foodItemService.deleteFoodItem(Integer.parseInt(id)), HttpStatus.OK);
	}
	
	@PutMapping("/admin/editMenu/{id}")
	public ResponseEntity<?> editFoodMenu(@PathVariable String id, @RequestBody FoodItem foodItem){
		return new ResponseEntity(foodItemService.editFoodMenu(foodItem, Integer.parseInt(id)),HttpStatus.OK);
	}
	

	// TEST FUNCTION
	//....
	// @PostMapping("/admin/selectMenu")
	// public ResponseEntity<?>  createMenu(@RequestBody Set<Integer> set){
	// 	Set<FoodItem> fis = new HashSet<>();
	// 	set.stream().forEach(ele->fis.add(fIRepository.findById(ele).get()));
		
	// 	for(FoodItem k:fis)
	// 	System.out.println(k.toString());

	// 	int cost = 0;
	// 	Menu m1 = new Menu();
	// 	m1.setFoodMenuItems(fis);
	// 	System.out.println(m1.getFoodMenuType());
	// 	m1.getFoodMenuCost();
	// 	menuRepository.save(m1);
	// 	return new ResponseEntity("OK", HttpStatus.OK);
	// }
	
}
