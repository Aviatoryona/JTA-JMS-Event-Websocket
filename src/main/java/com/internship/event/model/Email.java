package com.internship.event.model;

public class Email {
    public Email(String sender, String recipient, String message)
    {
        this.setSender(sender);
        this.setRecipient(recipient);
        this.setMessage(message);
    }
    private String sender;
    private String recipient;
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
