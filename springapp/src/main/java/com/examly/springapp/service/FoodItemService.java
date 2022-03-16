package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.FoodItem;

public interface FoodItemService {
	String addFoodItem(FoodItem foodItem);
	List<FoodItem> getFoodItems();
	String deleteFoodItem(Integer id);
	String editFoodMenu(FoodItem foodItem, Integer id);
}
