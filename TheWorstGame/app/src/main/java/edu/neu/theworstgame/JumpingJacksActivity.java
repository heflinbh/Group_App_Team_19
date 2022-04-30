package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.MessageFormat;

public class JumpingJacksActivity extends AppCompatActivity {

    TextView missionHeader;
    TextView missionDesc;
    TextView missionCounter;
    Missions missionsDatabase;
    boolean orientationChanged;
    int jumpCount = 0;
    User user;

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
        Bundle intent = getIntent().getExtras();
        user = (User) intent.getSerializable("user");
        Mission jumpingJacks = new Missions().getSensorMissions("ROTATION").get(0);


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
            user.addPoints(jumpingJacks.getPoints());
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
            user.updateUserToFirebase(myRef);
            Intent debriefActivity = new Intent(getApplicationContext(), DebriefActivity.class);
            startActivity(debriefActivity);
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