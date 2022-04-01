package edu.neu.theworstgame;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Missions {

    private ArrayList<Mission> missionList;

    public Missions() {
        missionList = new ArrayList<Mission>();
        missionList.add(new Mission("Snack time", "Make a snack to nourish your human body", "", 10));
        missionList.add(new Mission("Walk away", "Walk 100m away from where you are right now", "LOCATION", 0));
        missionList.add(new Mission("Tree gazing", "Find a tree, take an aesthetically pleasing photo and send it to a friend with a stupid caption", "CAMERA", 0));
        missionList.add(new Mission("Get moving", "Do 10 jumping jacks (hold the phone in your hand, we're checking)", "ROTATION", 1));
    }

    public ArrayList<Mission> getEasyMissions() {
        // Easy missions are missions without sensors, only time-based.
        ArrayList<Mission> easyList = getSensorMissions("");
        return easyList;
    }

    public ArrayList<Mission> getSensorMissions(String sensor) {
        ArrayList<Mission> sensorList = new ArrayList<>();
        for (Mission mission : missionList) {
            if (mission.getSensorUsed().equals(sensor.toUpperCase())) {
                sensorList.add(mission);
            }
        }
        return sensorList;
    }

    public Mission suggestMission(User user) {
        ArrayList<Mission> outstandingMissions = new ArrayList<>();
        for (Mission mission : missionList) {
            if (!user.getCompletedMissions().contains(mission)) {
                outstandingMissions.add(mission);
            }
        }
        int random = (int)(Math.random() * outstandingMissions.size());
        return outstandingMissions.get(random);
    }
}
