package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner timeSpinner;
    Spinner fatigueSpinner;
    RadioGroup productivitySelection;
    Intent missionResult;
    User user;

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

        productivitySelection = findViewById(R.id.radioGroup);
        Bundle intent = getIntent().getExtras();
        user = (User) intent.getSerializable("user");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.requestMissionButton) {

            Object timeChoice = timeSpinner.getSelectedItem();
            Object fatigueChoice = fatigueSpinner.getSelectedItem();
            int productivityChoice = productivitySelection.getCheckedRadioButtonId();
            missionResult = routeToMission(timeChoice, fatigueChoice, productivityChoice);
            startActivity(missionResult);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public Intent routeToMission(Object timeChoice, Object fatigueChoice, int productivityChoice) {
        int time;
        int fatigue;
        String productivity;

        if (timeChoice == null || timeChoice.toString().contains("<")) {
            time = 1;
        } else {
            time = Integer.parseInt(timeChoice.toString().split(" ")[0].replace(">",""));
        }
        if (fatigueChoice == null) {
            fatigue = 1;
        } else {
            fatigue = Integer.parseInt(fatigueChoice.toString());
        }
        if (productivityChoice == -1) {
            productivity = "no";
        } else {
            productivity = ((RadioButton) findViewById(productivityChoice)).getText().toString();
        }

        Intent missionActivity = null;
        if (time < 10) {
            missionActivity = new Intent(getApplicationContext(), JumpingJacksActivity.class);
        } else if (time >= 20) {
            missionActivity = new Intent(getApplicationContext(), MagicActivity.class);
        } else {
            missionActivity = new Intent(getApplicationContext(), CleanUpActivity.class);
        }
        missionActivity.putExtra("user", user);
        return missionActivity;
    }

}