package com.examly.springapp.model;

public class ReviewDTO {
	private int eventId;
	private ThemeReview review;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public ThemeReview getReview() {
		return review;
	}

	public void setReview(ThemeReview review) {
		this.review = review;
	}

}
