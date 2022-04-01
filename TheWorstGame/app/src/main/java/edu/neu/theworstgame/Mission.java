package edu.neu.theworstgame;

public class Mission {

    private String missionName;
    private String missionDescription;
    // if a sensor is used, otherwise empty String ""
    private String sensorUsed;
    // time limit for the task in minutes (if it's a time based task, otherwise 0)
    private int timeLimit;

    public Mission(String missionName, String missionDescription, String sensorUsed, int timeLimit) {
        this.missionName = missionName;
        this.missionDescription = missionDescription;
        this.sensorUsed = sensorUsed;
        this.timeLimit = timeLimit;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public String getSensorUsed() {
        return sensorUsed;
    }

    public int timeLimit() {
        return timeLimit;
    }

}