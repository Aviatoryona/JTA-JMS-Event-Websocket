package com.internship.event.action;

import com.internship.event.model.Email;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/default")
public class EmailService extends HttpServlet {
    @Inject
    Event<Email> event;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Email email = new Email("sender", "recipient", "message");
        event.fire(email); // execution doesn't wait for feedback from observer
        System.out.println("Continuing to other things");
    }
}
