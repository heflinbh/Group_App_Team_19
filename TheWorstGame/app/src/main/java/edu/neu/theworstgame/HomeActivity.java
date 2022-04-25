package edu.neu.theworstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    String callSign;
    String unitDesignation;
    User user;

    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle intent = getIntent().getExtras();
        callSign = intent.getString("Call Sign");
        unitDesignation = intent.getString("Unit Designation");
        user = (User) intent.getSerializable("user");

        welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Back again, " + user.getDisplayName() + "?");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startNewMissionButton:
                Intent assessmentActivity = new Intent(getApplicationContext(), AssessmentActivity.class);
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
            case R.id.aboutThisGame:
                Toast.makeText(this, "About This Game", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.missionHistory:
                Toast.makeText(this, "Mission History", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.leaderboard:
                Toast.makeText(this, "Leaderboard", Toast.LENGTH_SHORT).show();
                Intent socialActivity = new Intent(getApplicationContext(), SocialActivity.class);
                socialActivity.putExtra("Call Sign", callSign);
                startActivity(socialActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}