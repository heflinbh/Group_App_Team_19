package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MissionAlphaActivity extends AppCompatActivity {

    public int counter = 500;
    TextView missionTimer;
    TextView missionHeader;
    TextView missionDesc;
    TextView missionCounter;
    Missions missionsDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_alpha);
        missionTimer= findViewById(R.id.missionTimer);
        missionDesc = findViewById(R.id.missionDesc);
        missionHeader = findViewById(R.id.missionHeader);
        missionCounter = findViewById(R.id.missionCounter);
        missionsDatabase = new Missions();
        // need to figure out how to get the current user's name here
        user = new User("worst", "Novice");
        // hardcoded for now to get an easy mission with a simple timer
        Mission mission = missionsDatabase.getSensorMissions("ROTATION").get(0);

        missionDesc.setText(mission.getMissionDescription());
        missionHeader.setText(mission.getMissionName());
        missionCounter.setText(0);

        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                missionTimer.setText(String.valueOf(mission.getTimeLimit()*60));
                counter--;
            }
            public  void onFinish(){
                missionTimer.setText("Time is up!");
            }
        }.start();
    }
}