package com.internship.websocket.beans;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@ServerEndpoint("/chat-server/{phoneNumber}") // we use ws://localhost:8080/JEEAdvanced/chat-server
public class ChatServer {
    private Map<String, Session> sessions = new HashMap<>();
    // method to make a call every 5 seconds
    @Schedule(hour = "*", minute = "*", second = "*/5")
    public void timerService() throws IOException {
        for(Map.Entry session : sessions.entrySet())
            if(session.getValue() != null)
                ((Session) session.getValue()).getBasicRemote().sendText("Hope you are still alive: PINGPONG");
    }

    private String getPhoneNumber(Session session)
    {

        for(Map.Entry mSession : sessions.entrySet())
            if(((Session) mSession.getValue()).getId().equals(session.getId()))
                return mSession.getKey().toString();
            return null;
    }

    // connect
    @OnOpen // this method is invoked when a new client establishes a connection to the server
    public void onConnect(@PathParam("phoneNumber") String phoneNumber, Session session)
    {
        this.sessions.put(phoneNumber, session);
        System.out.println(" <<<<< Client connected. Phone # " + phoneNumber);
    }
    // disconnect
    @OnClose // this method is invoked when an active client disconnects from the server
    public void onDisconnect(Session session)
    {
        System.out.println(" <<<<< Client disconnected. Session #" + this.getPhoneNumber(session));

    }
    // process message
    @OnMessage // this method is invoked when an active client sends a message
    public void onMessage(Session session, String message) throws IOException {
        System.out.println(" <<<<< Message received from client session #" + this.getPhoneNumber(session));
        System.out.println(" <<<<< Message received is: " + message);
        session.getBasicRemote().sendText("Message received [Server talking to client]");
    }
}
