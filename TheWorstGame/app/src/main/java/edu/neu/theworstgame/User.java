package edu.neu.theworstgame;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;
    String password;
    private ArrayList<Mission> completedMissions;
    private String displayName;
    private long lastMissionTimestamp;
    private int missionAccomplished;
    private int points;

    public User() {
    };

    public User(String username
            , String password
            , String displayName
            , long lastMissionTimestamp
            , int missionAccomplished
            , int points
    ) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.completedMissions = new ArrayList<>();
        this.lastMissionTimestamp = lastMissionTimestamp;
        this.missionAccomplished = missionAccomplished;
        this.points = points;
    }

    /**
     * Initial new user
     * @param username String
     * @param password String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.displayName = username;
        this.completedMissions = new ArrayList<>();
        this.lastMissionTimestamp = -1;
        this.missionAccomplished = 0;
        this.points = 0;
    }

    public void addCompletedMission(Mission mission) {
        completedMissions.add(mission);
    }

    public ArrayList<Mission> getCompletedMissions() {
        return completedMissions;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public long getLastMissionTimestamp() {
        return lastMissionTimestamp;
    }

    public int getMissionAccomplished() {
        return missionAccomplished;
    }

    public int getPoints() {
        return points;
    }

    /**
     * tier implemented here
     * @return tier as String
     */
    public String calculateTier() {
        if (points == 0) {
            return "Unranked";
        } else if (points <= 3) {
            return "Bronze";
        } else if (points <= 10) {
            return "Silver";
        } else if (points <= 25) {
            return "Gold";
        } else if (points <= 50) {
            return "Platinum";
        } else if (points <= 100) {
            return "Diamond";
        } else {
            return "Crystal";
        }
    }

}
