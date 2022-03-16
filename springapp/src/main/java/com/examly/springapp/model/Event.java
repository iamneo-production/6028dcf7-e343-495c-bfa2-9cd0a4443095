package com.examly.springapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *  This class is in development.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

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
	private Date eventDate; //2012-04-23T18:25:43.511Z

//	@NotNull
//	@OneToOne(cascade = CascadeType.PERSIST)
//	private Theme eventTheme;
	
	@NotNull
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Menu eventMenu;
	
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="events_addon",
			joinColumns = {@JoinColumn(name="eventId",nullable = false)},
			inverseJoinColumns = {@JoinColumn(name="addonId", nullable = false)}
			)
	private Set<AddOn> eventAddonsId = new HashSet<>();

//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private User bookedByUser;

	@NotNull
	private long eventCost;

	public Event() {
		super();
	}
	
	

//	public Event(String eventName,
//			 Date eventDate) {
//		this.eventName = eventName;
//		this.applicantName = "Test Name";
//		this.appicantAddress = "appicantAddress";
//		this.applicantMobile = "applicantMobile";
//		this.applicantEmail = "applicantEmail";
//		this.eventAddress = "eventAddress";
//		this.eventDate = eventDate;
//		this.eventCost = 100l;
//	}



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

//	public Menu getEventMenuId() {
//		return eventMenuId;
//	}
//
//	public void setEventMenuId(Menu eventMenuId) {
//		this.eventMenuId = eventMenuId;
//	}



	public Set<AddOn> getEventAddonsId() {
		return eventAddonsId;
	}

	public void setEventAddonsId(Set<AddOn> eventAddonsId) {
		this.eventAddonsId = eventAddonsId;
	}

//	public User getBookedByUser() {
//		return bookedByUser;
//	}
//
//	public void setBookedByUser(User bookedByUser) {
//		this.bookedByUser = bookedByUser;
//	}

	public long getEventCost() {
		return eventCost;
	}

	public void setEventCost(long eventCost) {
		this.eventCost = eventCost;
	}



	public Menu getEventMenu() {
		return eventMenu;
	}



	public void setEventMenu(Menu eventMenu) {
		this.eventMenu = eventMenu;
	}



//	@Override
//	public String toString() {
//		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", applicantName=" + applicantName
//				+ ", appicantAddress=" + appicantAddress + ", applicantMobile=" + applicantMobile + ", applicantEmail="
//				+ applicantEmail + ", eventAddress=" + eventAddress + ", eventDate=" + eventDate + ", eventMenuId="
//				+ eventMenuId + ", eventAddonsId=" + eventAddonsId + ", bookedByUser=" + bookedByUser + ", eventCost="
//				+ eventCost + "]";
//	}
	
	

}
