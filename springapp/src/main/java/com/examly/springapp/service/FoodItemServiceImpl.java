package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateValueException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.FoodItem;
import com.examly.springapp.repository.FoodItemRepository;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	FoodItemRepository foodItemRepository;

	@Override
	public String addFoodItem(FoodItem foodItem) {
		if (foodItemRepository.existsByFoodItemName(foodItem.getFoodItemName())) {
			FoodItem existingFoodItem = foodItemRepository.findByFoodItemName(foodItem.getFoodItemName()).get();
			if (existingFoodItem.getIsActive()) {
				throw new DuplicateValueException("FoodItem", "foodItemName", foodItem.getFoodItemName());
			}
			existingFoodItem.setIsActive(true);
			existingFoodItem.setFoodItemPrice(foodItem.getFoodItemPrice());
			existingFoodItem.setFoodItemCategory(foodItem.getFoodItemCategory());
			existingFoodItem.setFoodItemImage(foodItem.getFoodItemImage());
			foodItemRepository.save(existingFoodItem);
			return "Food Item Added";
		}
		foodItemRepository.save(foodItem);
		return "Food Item Added";
	}

	@Override
	public List<FoodItem> getFoodItems() {
		List<FoodItem> result = new ArrayList<>();
		foodItemRepository.findAll().stream().filter(ele -> ele.getIsActive()).forEach(result::add);
		return result;
	}

	@Override
	public String deleteFoodItem(Integer id) {
		Optional<FoodItem> fOptional = foodItemRepository.findById(id);
		if (fOptional.isEmpty()) {
			throw new ResourceNotFoundException("Item not found");
		}

		FoodItem fi = fOptional.get();
		if (!fi.getIsActive()) {
			throw new ResourceNotFoundException("Item not found");
		}
		fi.setIsActive(false);
		foodItemRepository.save(fi);
		return "Food item deleted";
	}

	@Override
	public String editFoodMenu(FoodItem foodItem, Integer id) {
		if (!foodItemRepository.existsById(id) || !foodItemRepository.findById(id).get().getIsActive()) {
			throw new ResourceNotFoundException("Item not found");
		}
		FoodItem existingFoodItem = foodItemRepository.findById(id).get();

		// If request has new foodItemName - proceed
		// If foodItemName is not new, check if foodItem with another id wants that old name
		// if yes throw error else implies its changing its own properties, in this case allow
		if (foodItemRepository.existsByFoodItemName(foodItem.getFoodItemName())
				&& foodItemRepository.findByFoodItemName(foodItem.getFoodItemName()).get()
						.getFoodItemId() != existingFoodItem.getFoodItemId()) {
			throw new DuplicateValueException("FoodItem", "foodItemName", foodItem.getFoodItemName());
		}

		existingFoodItem.setFoodItemName(foodItem.getFoodItemName());
		existingFoodItem.setFoodItemPrice(foodItem.getFoodItemPrice());
		existingFoodItem.setFoodItemCategory(foodItem.getFoodItemCategory());
		existingFoodItem.setFoodItemImage(foodItem.getFoodItemImage());
		foodItemRepository.save(existingFoodItem);
		return "Food item edited";
	}

}
