package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner timeSpinner;
    Spinner fatigueSpinner;
    RadioButton productivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        timeSpinner = findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        timeSpinner.setOnItemSelectedListener(this);

        fatigueSpinner = findViewById(R.id.fatigueSpinner);

        ArrayAdapter<CharSequence> fatigueAdapter = ArrayAdapter.createFromResource(this,
                R.array.fatigue, android.R.layout.simple_spinner_dropdown_item);
        fatigueSpinner.setAdapter(fatigueAdapter);
        fatigueSpinner.setOnItemSelectedListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestMissionButton:
                Intent alphaMissionActivity = new Intent(getApplicationContext(), MissionAlphaActivity.class);
                startActivity(alphaMissionActivity);
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}