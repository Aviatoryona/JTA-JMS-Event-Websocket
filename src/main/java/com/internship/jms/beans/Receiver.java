package com.internship.jms.beans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Date;

public class Receiver implements MessageListener {
    private static int id;
    private Date createDatetime;
    private int receiverId;
    public Receiver()
    {
        id ++;
        receiverId = id;
        createDatetime = new Date();
        System.out.println("Receiver ID " + receiverId + " has been registered at " + createDatetime);
    }
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Receiver ID " + receiverId + " [" + createDatetime + "]" + " has received message ==> : " + ((TextMessage) message).getText());
            // send your sms message
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
