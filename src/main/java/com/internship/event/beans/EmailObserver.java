package com.internship.event.beans;

import com.internship.event.model.Email;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmailObserver {
    @Inject
    private Event<Email> emailEvent_;
    public void notificationReceived(@Observes Email email)
    {
        // do the work that needs doing
        System.out.println("Email Event Received: Sender: " + email.getSender());
    }
}
