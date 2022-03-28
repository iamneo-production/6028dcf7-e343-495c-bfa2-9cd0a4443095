package com.examly.springapp.model;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	@NotNull
	private String eventName;
	private String applicantName;
	private String applicantAddress;
	private String applicantMobile;
	private String applicantEmail;
	private String eventAddress;
	private String eventStatus; // ENUM [BOOKED, CANCELLED, COMPLETED, REFUNDED]
	private Date eventDate; // 2012-04-23T18:25:43.511Z

	@OneToOne
	@JoinColumn(name="theme_id")
	private Theme eventTheme;

	@NotNull
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "menu_id")
	private Menu eventMenu;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "event_addons", joinColumns = {
			@JoinColumn(name = "eventId", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "addonId", nullable = false) })
	private Set<AddOn> eventAddonsId = new HashSet<>();

	@NotNull
	private long eventCost;

	public Event() {
		super();
	}

	public Event(String eventName) {
		super();
		this.eventName = eventName;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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

	public String getApplicantMobile() {
		return applicantMobile;
	}

	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
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

	public Theme getEventTheme() {
		return eventTheme;
	}

	public void setEventTheme(Theme eventTheme) {
		this.eventTheme = eventTheme;
	}

	public Menu getEventMenu() {
		return eventMenu;
	}

	public void setEventMenu(Menu eventMenu) {
		this.eventMenu = eventMenu;
	}

	public Set<AddOn> getEventAddonsId() {
		return eventAddonsId;
	}

	public void setEventAddonsId(Set<AddOn> eventAddonsId) {
		this.eventAddonsId = eventAddonsId;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public long getEventCost() {
		return eventCost;
	}

	public void setEventCost(long eventCost) {
		this.eventCost = eventCost;
	}
}