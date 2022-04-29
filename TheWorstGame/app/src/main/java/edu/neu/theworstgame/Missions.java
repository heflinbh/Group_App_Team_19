package edu.neu.theworstgame;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Missions {

    private ArrayList<Mission> missionList;

    public Missions() {
        missionList = new ArrayList<Mission>();
        missionList.add(new Mission("Clean up", "Secretly up a common area in your habitat to make the humans around you relaxed", "", 10));
        missionList.add(new Mission("Source magic", "Locate a nearby tree and assess its capabilities to generate magic. Report back its quotient", "", 20));
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

    public Mission suggestMission(User user, int timeAvailable, int tiredness, boolean productive) {
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
