package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CleanUpActivity extends AppCompatActivity {

    TextView missionTimer;
    TextView missionHeader;
    TextView missionDesc;
    Missions missionsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_alpha);
        missionTimer= findViewById(R.id.missionTimer);
        missionDesc = findViewById(R.id.missionDesc);
        missionHeader = findViewById(R.id.missionHeader);
        missionsDatabase = new Missions();
        Mission mission = missionsDatabase.getEasyMissions().get(0);
        Bundle intent = getIntent().getExtras();
        User user = (User) intent.getSerializable("user");

        missionDesc.setText(mission.getMissionDescription());
        missionHeader.setText(mission.getMissionName());
        int timeLimit = mission.getTimeLimit()*60;

        new CountDownTimer(timeLimit*1000, 1000){
            public void onTick(long millisUntilFinished){
                missionTimer.setText(millisUntilFinished / 1000 + " seconds left.");
            }
            public  void onFinish(){
                missionTimer.setText("Time is up!");
                user.addPoints(mission.getPoints());
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
                user.updateUserToFirebase(myRef);
                Intent debriefActivity = new Intent(getApplicationContext(), DebriefActivity.class);
                startActivity(debriefActivity);
            }
        }.start();
    }
}