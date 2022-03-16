package com.examly.springapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodMenuId;
	private String foodMenuType;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="menu_items",
			joinColumns = {@JoinColumn(name="foodMenuId")},
			inverseJoinColumns = {@JoinColumn(name="foodItemId")}
			)
	private Set<FoodItem> foodMenuItems = new HashSet<>();
	private long foodMenuCost;

	public Menu() {
		super();
	}

	public int getFoodMenuId() {
		return foodMenuId;
	}

	public void setFoodMenuId(int foodMenuId) {
		this.foodMenuId = foodMenuId;
	}

	public String getFoodMenuType() {
		if(this.foodMenuType!=null) {
			return this.foodMenuType;
		}
		for(FoodItem fi:foodMenuItems) {
			if(fi.getFoodItemCategory().equals("non-veg")) {
				setFoodMenuType("non-veg");
				return getFoodMenuType();
			}
		}
		setFoodMenuType("veg");
		return getFoodMenuType();
	}

	public void setFoodMenuType(String foodMenuType) {
		this.foodMenuType = foodMenuType;
	}

	public Set<FoodItem> getFoodMenuItems() {
		return foodMenuItems;
	}

	public void setFoodMenuItems(Set<FoodItem> foodMenuItems) {
		this.foodMenuItems = foodMenuItems;
	}

	public long getFoodMenuCost() {
		if(this.foodMenuCost!=0) {
			return this.foodMenuCost;
		}
		long cost = 0;
		for(FoodItem fi:foodMenuItems) {
			cost+=fi.getFoodItemPrice();
		}
		setFoodMenuCost(cost);
		return getFoodMenuCost();
	}

	public void setFoodMenuCost(long foodMenuCost) {
		this.foodMenuCost = foodMenuCost;
	}

}
