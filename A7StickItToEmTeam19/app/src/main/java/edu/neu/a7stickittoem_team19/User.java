package edu.neu.a7stickittoem_team19;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private String username;
    private String timestamp;

    public User() {}

    public User(String username) {
        this.username = username;
        this.timestamp = createTimestamp();
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
}
