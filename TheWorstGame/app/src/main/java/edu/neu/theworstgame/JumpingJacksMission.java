package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.text.MessageFormat;

public class JumpingJacksMission extends AppCompatActivity {

    public int counter = 500;
    TextView missionHeader;
    TextView missionDesc;
    TextView missionCounter;
    Missions missionsDatabase;
    boolean orientationChanged;
    int jumpCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumping_jacks);
        missionDesc = findViewById(R.id.missionDesc);
        missionHeader = findViewById(R.id.missionHeader);
        missionCounter = findViewById(R.id.missionCounter);
        missionsDatabase = new Missions();
        Mission mission = missionsDatabase.getSensorMissions("ROTATION").get(0);
        orientationChanged = false;
        missionDesc.setText(mission.getMissionDescription());
        missionHeader.setText(mission.getMissionName());
        jumpCount = savedInstanceState != null ? savedInstanceState.getInt("count", jumpCount) : 0;
        if (jumpCount > 0 && jumpCount< 20) {
            int missionJumpCount;
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                missionJumpCount = Math.floorDiv(jumpCount, 2);
            } else {
                missionJumpCount = (int) Math.ceil(jumpCount/2);
            }
            if (missionJumpCount == 1) {
                missionCounter.setText("You've done " + missionJumpCount + " jumping jack.");
            } else {
                missionCounter.setText("You've done " + missionJumpCount + " jumping jacks.");

            }
        } else if (jumpCount >= 20){
            missionCounter.setText("Look at you! You did it!");
            missionDesc.setText("Mission accomplished. Stop jumping. Humans will suspect you.");
            // TODO: open debrief activity here once it's done
        } else {
            missionCounter.setText("You've done 0 jumping jacks. What's the hold up?");
        }

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        jumpCount++;
        savedInstanceState.putInt("count", jumpCount);
    }
}