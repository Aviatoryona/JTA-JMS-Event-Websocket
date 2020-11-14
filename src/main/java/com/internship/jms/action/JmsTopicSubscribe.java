package com.internship.jms.action;

import com.internship.jms.beans.JmsBean;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/jms-topic-subscribe")
public class JmsTopicSubscribe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new JmsBean().topicSubscribe();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
