package edu.neu.a7stickittoem_team19;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private String sender;
    private String receiver;
    private String messageType;
    private String timestamp;

    public Message () {}

    public Message (String sender, String receiver, String messageType) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = messageType;
        this.timestamp = createTimestamp();
    }

    private String createTimestamp() {
        Date date = new Date();
        Timestamp stamp = new Timestamp(date.getTime());
        return String.valueOf(stamp.getTime());
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
