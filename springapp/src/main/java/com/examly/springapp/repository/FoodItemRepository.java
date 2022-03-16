package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer>{
	public boolean existsByFoodItemName(String foodItemName);
	public Optional<FoodItem> findByFoodItemName(String name);
}
