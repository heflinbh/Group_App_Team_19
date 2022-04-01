package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String callSign;
    String unitDesignation;

    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle intent = getIntent().getExtras();
        callSign = intent.getString("Call Sign");
        unitDesignation = intent.getString("Unit Designation");

        welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Back again, " + callSign + "?");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startNewMissionButton:

                Intent assessmentActivity = new Intent(getApplicationContext(), AssessmentActivity.class);
                startActivity(assessmentActivity);

                break;
        }
    }
}