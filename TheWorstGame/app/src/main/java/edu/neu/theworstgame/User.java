package edu.neu.theworstgame;

import java.util.ArrayList;

public class User {

    private String username;
    private ArrayList<Mission> completedMissions;
    // Haven't decided how to implement tier yet
    private String tier;

    public User(String username) {
        this.username = username;
        this.completedMissions = new ArrayList<>();
    }

    public void addCompletedMission(Mission mission) {
        completedMissions.add(mission);
    }

    public ArrayList<Mission> getCompletedMissions() {
        return completedMissions;
    }


}
