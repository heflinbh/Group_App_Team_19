package edu.neu.theworstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {

    String callSign;
    String unitDesignation;
    User user;
    ProgressBar progress;

    TextView welcomeTextView;
    TextView tierProgress;
    TextView nextReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progress = findViewById(R.id.progressBar2);
        tierProgress = findViewById(R.id.tierProgressTextView);
        nextReward = findViewById(R.id.nextRewardTextView);

        Bundle intent = getIntent().getExtras();
        callSign = intent.getString("Call Sign");
        unitDesignation = intent.getString("Unit Designation");
        user = (User) intent.getSerializable("user");

        welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Back again, " + user.getDisplayName() + "?");
        progress.setProgress(user.getPoints(),true);
        tierProgress.setText("Tier Progress: " + user.getPoints() + " Points - " + user.calculateTier());
        nextReward.setText("Next Reward: " + user.calculateNextTier());


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startNewMissionButton:
                Intent assessmentActivity = new Intent(getApplicationContext(), AssessmentActivity.class);
                assessmentActivity.putExtra("user", user);
                startActivity(assessmentActivity);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
//            case R.id.aboutThisGame:
//                Toast.makeText(this, "About This Game", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.profile:
//                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.missionHistory:
//                Toast.makeText(this, "Mission History", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.leaderboard:
                Toast.makeText(this, "Leaderboard", Toast.LENGTH_SHORT).show();
                Intent socialActivity = new Intent(getApplicationContext(), SocialActivity.class);
                socialActivity.putExtra("Call Sign", callSign);
                socialActivity.putExtra("user", user);
                startActivity(socialActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}