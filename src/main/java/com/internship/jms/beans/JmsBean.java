package com.internship.jms.beans;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;

public class JmsBean {
    InitialContext context;
    QueueConnectionFactory queueConnectionFactory;
    TopicConnectionFactory topicConnectionFactory;
    public void queueSend() throws NamingException, JMSException {
        context = new InitialContext();
        queueConnectionFactory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");
        QueueConnection connection = queueConnectionFactory.createQueueConnection();
        connection.start();
        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) context.lookup("java:/jms/queue/DLQ"); // is same as this
        QueueSender sender = session.createSender(queue); // create a sender
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("This is the message from the sender at " + new Date());
        sender.send(textMessage);
        System.out.println("message has been sent");
    }

    public void queueReceive() throws NamingException, JMSException
    {
        context = new InitialContext();
        queueConnectionFactory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");
        QueueConnection connection = queueConnectionFactory.createQueueConnection();
        connection.start();
        QueueSession session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) context.lookup("java:/jms/queue/DLQ"); // this queue
        QueueReceiver receiver = session.createReceiver(queue);
        receiver.setMessageListener(new Receiver()); // registration of a receiver (message receiver)
    }

    public void topicPublish() throws NamingException, JMSException {
        context = new InitialContext();
        topicConnectionFactory = (TopicConnectionFactory) context.lookup("java:/ConnectionFactory");
        TopicConnection connection = topicConnectionFactory.createTopicConnection();
        connection.start();
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup("java:/jms/topic/MyTopic"); // is same as this
        TopicPublisher publisher = session.createPublisher(topic); // create a sender
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("This is the message from the sender at " + new Date());
        publisher.publish(textMessage);
        System.out.println("message has been published");

    }

    public void topicSubscribe() throws NamingException, JMSException {
        context = new InitialContext();
        topicConnectionFactory = (TopicConnectionFactory) context.lookup("java:/ConnectionFactory");
        TopicConnection connection = topicConnectionFactory.createTopicConnection();
        connection.start();
        TopicSession session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup("java:/jms/topic/MyTopic"); // this queue
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(new Receiver()); // registration of a receiver (message receiver)
    }
}
