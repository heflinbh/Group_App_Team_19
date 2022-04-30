package edu.neu.theworstgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MagicActivity extends AppCompatActivity {

    TextView missionTimer;
    TextView missionHeader;
    TextView missionDesc;
    Button captureButton;
    Missions missionsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic);
        missionTimer= findViewById(R.id.missionTimer);
        missionDesc = findViewById(R.id.missionDesc);
        missionHeader = findViewById(R.id.missionHeader);
        captureButton = findViewById(R.id.captureButton);
        missionsDatabase = new Missions();
        Mission mission = missionsDatabase.getSensorMissions("CAMERA").get(0);
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