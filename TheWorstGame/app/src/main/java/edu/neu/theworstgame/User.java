package edu.neu.theworstgame;

import java.util.ArrayList;

public class User {

    private String username;
    private ArrayList<Mission> completedMissions;
    // Haven't decided how to implement tier yet
    String password;
    private String tier;
    private String display_name;
    private long last_mission_timestamp;
    private int mission_accomplished;
    private int points;

    public User() {
    };

    public User(String username
            , String password
            , String tier
            , String display_name
            , long last_mission_timestamp
            , int mission_accomplished
            , int points
    ) {
        this.username = username;
        this.password = password;
        this.tier = tier;
        this.display_name = display_name;
        this.completedMissions = new ArrayList<>();
        this.last_mission_timestamp = last_mission_timestamp;
        this.mission_accomplished = mission_accomplished;
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
        this.display_name = username;
        this.completedMissions = new ArrayList<>();
        this.last_mission_timestamp = -1;
        this.mission_accomplished = 0;
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
        return display_name;
    }

    public long getLastMissionTimestamp() {
        return last_mission_timestamp;
    }

    public int getMissionAccomplished() {
        return mission_accomplished;
    }

    public int getPoints() {
        return points;
    }

}
