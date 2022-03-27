package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="addon")
public class AddOn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addonId;
	@Column(unique = true, nullable = false)
	private String addonName;
	@NotNull
	private String addonDescription;
	@NotNull
	private long addonPrice;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "addOn_id")
	private List<AddOnReview> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "eventAddonsId")
	private Set<Event> events = new HashSet<>();

	private boolean isActive = true;

	public AddOn() {
		super();
	}

	public int getAddonId() {
		return addonId;
	}

	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

	public String getAddonDescription() {
		return addonDescription;
	}

	public void setAddonDescription(String addonDescription) {
		this.addonDescription = addonDescription;
	}

	public long getAddonPrice() {
		return addonPrice;
	}

	public void setAddonPrice(long addonPrice) {
		this.addonPrice = addonPrice;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<AddOnReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<AddOnReview> reviews) {
		this.reviews = reviews;
	}

}
