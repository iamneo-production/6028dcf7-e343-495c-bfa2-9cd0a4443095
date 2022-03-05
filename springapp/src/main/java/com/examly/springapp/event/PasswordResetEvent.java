package com.examly.springapp.event;

import org.springframework.context.ApplicationEvent;

import com.examly.springapp.model.User;

public class PasswordResetEvent extends ApplicationEvent{
	private User user;
	private String appURL;

	public PasswordResetEvent(User user, String appURL) {
		super(user);
		this.user = user;
		this.appURL = appURL;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
}
