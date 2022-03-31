package com.examly.springapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodItemId;
	@Column(unique = true, nullable = false)
	private String foodItemName;
	@NotNull
	@Min(value=1L)
	private long foodItemPrice;
	@NotNull
	private String foodItemCategory;
	@NotNull
	private String foodItemImage;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "foodMenuItems")
	private Set<Menu> menus = new HashSet<>();
	
	@NotNull
	@JsonIgnore
	private Boolean isActive = true;

	public FoodItem() {
		super();
	}

	public int getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public long getFoodItemPrice() {
		return foodItemPrice;
	}

	public void setFoodItemPrice(long foodItemPrice) {
		this.foodItemPrice = foodItemPrice;
	}

	public String getFoodItemCategory() {
		return foodItemCategory;
	}

	public void setFoodItemCategory(String foodItemCategory) {
		this.foodItemCategory = foodItemCategory;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public String getFoodItemImage() {
		return foodItemImage;
	}

	public void setFoodItemImage(String foodItemImage) {
		this.foodItemImage = foodItemImage;
	}
	
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FoodItem [foodItemId=" + foodItemId + ", foodItemName=" + foodItemName + ", foodItemPrice="
				+ foodItemPrice + ", foodItemCategory=" + foodItemCategory + ", foodItemImage=" + foodItemImage
				+"]";
	}
	
	
}
