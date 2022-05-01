package edu.neu.theworstgame;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Missions {

    private ArrayList<Mission> missionList;

    public Missions() {
        missionList = new ArrayList<Mission>();
        missionList.add(new Mission(
                "Strengthen Your Cover",
                "Use your security training to secretly beautify a common area in around you to lull humans around you into relaxing their vigilance. Do not be discovered, and work fast!",
                "",
                10)
        );
        missionList.add(new Mission(
                "Discover Usable Magic Sources",
                "Locate a nearby tree or grove and assess its magic generative potential. Use the usual techniques, and report back its quotient. " +
                        "(Hint: human magical senses are rather stunted, try some meditation techniques to see if you can improve your senses)",
                "CAMERA",
                20)
        );
        missionList.add(new Mission(
                "Avoid Surveillance",
                "Sometimes you need to stretch your capability even under unfavorable conditions. \n\n" +
                        "Go to a public location without being observed, and perform 10 jumping jacks without anyone noticing " +
                        "(hold the phone in your hand for the jumping jacks, we're checking).",
                "ROTATION",
                30)
        );
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
