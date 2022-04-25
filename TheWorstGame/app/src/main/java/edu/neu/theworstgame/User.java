package edu.neu.theworstgame;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;
    String password;
    private ArrayList<Mission> completedMissions;
    // Haven't decided how to implement tier yet
    private String tier;
    private String displayName;
    private long lastMissionTimestamp;
    private int missionAccomplished;
    private int points;

    public User() {
    };

    public User(String username
            , String password
            , String tier
            , String displayName
            , long lastMissionTimestamp
            , int missionAccomplished
            , int points
    ) {
        this.username = username;
        this.password = password;
        this.tier = tier;
        this.displayName = displayName;
        this.completedMissions = new ArrayList<>();
        this.lastMissionTimestamp = lastMissionTimestamp;
        this.missionAccomplished = missionAccomplished;
        this.points = points;
    }

    /**
     * Initial user with no mission history
     * no display name entered use username
     * @param username
     * @param tier
     */
    public User(String username, String tier) {
        this.username = username;
        this.password = "";
        this.tier = tier;
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

}
