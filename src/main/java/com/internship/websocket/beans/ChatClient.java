package com.internship.websocket.beans;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class ChatClient {
    private String phoneNumber;
    private Session session; // channel that connects the client and the server (pipe)
    public ChatClient(String phoneNumber) throws DeploymentException, IOException, URISyntaxException {
        this.phoneNumber = phoneNumber;
        this.initConnection(phoneNumber);
    }
    private void initConnection(String phoneNumber) throws URISyntaxException, IOException, DeploymentException {
        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        webSocketContainer.connectToServer(this, new URI("ws://localhost:8080/JEEAdvanced/chat-server/" + phoneNumber));
    }
    public void sendMessage(String message) throws IOException, InterruptedException {
        while(this.session == null)
            Thread.sleep(2000);
        session.getBasicRemote().sendText(message);
    }
    @OnOpen // this method is invoked when a new client establishes a connection to the server
    public void onConnect(Session session)
    {
        this.session = session;
        System.out.println("Client connected. Session #" + this.phoneNumber + " >>>> ");
    }
    // disconnect
    @OnClose // this method is invoked when an active client disconnects from the server
    public void onDisconnect(Session session)
    {
        System.out.println("Client disconnected. Session #" + this.phoneNumber + " >>>> ");

    }
    // process message
    @OnMessage // this method is invoked when an active client sends a message
    public void onMessage(Session session, String message) throws IOException, InterruptedException {
        System.out.println("Message received from client session #" + this.phoneNumber + " >>>> ");
        System.out.println("Message received is: " + message + " >>>> ");
        if(message.equals("Hope you are still alive: PINGPONG"))
            this.sendMessage("Am OK, thanks. [" + this.phoneNumber + "]");
    }
}
