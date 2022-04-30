package edu.neu.theworstgame;

import com.google.firebase.database.DatabaseReference;

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

    /**
     * set new password
     * @param password1 new password
     */
    public void setPassword(String password1) {
        password = password1;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * set new display name
     * @param displayName1 new display name
     */
    public void setDisplayName(String displayName1) {
        displayName = displayName1;
    }

    public long getLastMissionTimestamp() {
        return lastMissionTimestamp;
    }

    /**
     * set lass mission ts to now
     */
    public void setLastMissionTimestamp() {
        lastMissionTimestamp = System.currentTimeMillis();
    }

    public int getMissionAccomplished() {
        return missionAccomplished;
    }

    /**
     * add 1 mission
     * @return mission +1
     */
    public int addOneMissionAccomplished() {
        missionAccomplished++;
        return missionAccomplished;
    }

    public int getPoints() {
        return points;
    }

    /**
     * add x points to current
     * @param adding x
     * @return points + x
     */
    public int addPoints(int adding) {
        points += adding;
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


    /**
     * For example:
     *
     * DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
     * user.updateUserToFirebase(myRef);
     *
     * @param myRefUsers DatabaseReference path "users"
     */
    public void updateUserToFirebase (DatabaseReference myRefUsers) {
        myRefUsers.child(this.username).setValue(this);
    }

    public String calculateNextTier() {
        String currentTier = this.calculateTier();
        switch (currentTier) {
            case "Unranked":
                return "Bronze";
            case "Bronze":
                return "Silver";
            case "Silver":
                return "Gold";
            case "Gold":
                return "Platinum";
            case "Platinum":
                return "Diamond";
            case "Diamond":
                return "Crystal";
            default:
                return "Enlightenment";
        }
    }
}
