package edu.neu.a7stickittoem_team19;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private String username;
    private String timestamp;
    private String quantStampA;
    private String quantStampB;


    public User() {}

    public User(String username) {
        this.username = username;
        this.timestamp = createTimestamp();
        this.quantStampA = "0";
        this.quantStampB = "0";
    }

    private String createTimestamp() {
        Date date = new Date();
        Timestamp stamp = new Timestamp(date.getTime());
        return String.valueOf(stamp.getTime());
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() { return timestamp; }

    public String getQuantStampA() {
        return quantStampA;
    }

    public String getQuantStampB() {
        return quantStampB;
    }

    public void incrementA() {
        int temp = Integer.valueOf(this.quantStampA) + 1;
        this.quantStampA = String.valueOf(temp);
    }

    public void incrementB() {
        int temp = Integer.valueOf(this.quantStampB) + 1;
        this.quantStampB = String.valueOf(temp);
    }

    public int getStickersSent() {
        return Integer.valueOf(quantStampA) + Integer.valueOf(quantStampB);
    }

    public boolean equals(User other) {
        return username == other.getUsername();
    }
}
