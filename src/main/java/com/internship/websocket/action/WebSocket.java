package com.internship.websocket.action;

import com.internship.websocket.beans.ChatClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet("/websocket")
public class WebSocket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatClient chatClient = null; // initialize chat client
        try {
            chatClient = new ChatClient(req.getParameter("phoneNumber"));
            chatClient.sendMessage(req.getParameter("message")); // make chat client send message to chat server
        } catch (DeploymentException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
