package edu.neu.theworstgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MagicActivity extends AppCompatActivity {

    TextView missionTimer;
    TextView missionHeader;
    TextView missionDesc;
    Button captureButton;
    Button quitButton;
    Missions missionsDatabase;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic);
        missionTimer= findViewById(R.id.missionTimer);
        missionDesc = findViewById(R.id.missionDesc);
        missionHeader = findViewById(R.id.missionHeader);
        captureButton = findViewById(R.id.captureButton);
        quitButton = findViewById(R.id.quitButton);



        missionsDatabase = new Missions();
        Mission mission = missionsDatabase.getSensorMissions("CAMERA").get(0);
        Bundle intent = getIntent().getExtras();
        User user = (User) intent.getSerializable("user");
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MagicActivity.this);
                builder.setMessage("Are you really going to quit?")
                        .setTitle("Quitter...")
                        .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                                homeActivity.putExtra("user", user);
                                startActivity(homeActivity);                            }
                        })
                        .setNegativeButton("Resume", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                cancelDialog();
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        });
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
                debriefActivity.putExtra("user", user);
                startActivity(debriefActivity);
            }
        }.start();
    }


    public void cancelDialog() {
        alert.cancel();
    }
}