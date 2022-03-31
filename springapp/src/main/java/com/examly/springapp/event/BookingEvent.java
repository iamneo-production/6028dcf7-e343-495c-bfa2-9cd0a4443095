package com.examly.springapp.event;

import com.examly.springapp.model.Event;
import com.examly.springapp.model.User;

import org.springframework.context.ApplicationEvent;

public class BookingEvent extends ApplicationEvent {
    private String appURL;
    private Event _event;
    private User user;

    public BookingEvent(String appURL, Event event, User user) {
        super(event);
        this.appURL = appURL;
        this._event = event;
        this.user = user;
    }

    public String getAppURL() {
        return appURL;
    }

    public void setAppURL(String appURL) {
        this.appURL = appURL;
    }

    public Event getEvent() {
        return _event;
    }

    public void setEvent(Event event) {
        this._event = event;
    }

    public Event get_event() {
        return _event;
    }

    public void set_event(Event _event) {
        this._event = _event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
