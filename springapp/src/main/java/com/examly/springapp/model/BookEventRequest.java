package com.examly.springapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookEventRequest {
	private String eventName;
	private String applicantName;
	private String applicantAddress;

	/*
	 * Mobile, Email get from User object
	 */
	private String eventAddress;
	private Date eventDate;
	private Set<Integer> foodItemsId = new HashSet<>();
	private Set<Integer> addOnsId = new HashSet<>();
	private Integer themeId;

	public BookEventRequest() {
		super();
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Set<Integer> getFoodItemsId() {
		return foodItemsId;
	}

	public void setFoodItemsId(Set<Integer> foodItemsId) {
		this.foodItemsId = foodItemsId;
	}

	public Set<Integer> getAddOnsId() {
		return addOnsId;
	}

	public void setAddOnsId(Set<Integer> addOnsId) {
		this.addOnsId = addOnsId;
	}

}
