package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class StoryStart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView current;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_start);

        current = findViewById(R.id.story_text1);
        current.setText(
                "Apologies for the \"forget me\" spell. We operate in an hostile world. \n\n" +
                "You are here, so I assume you have regained enough of your previous memories to remember this channel. \n"
        );
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.story_proceed1:
                setContentView(R.layout.story2);
                current = findViewById(R.id.story_text2);
                current.setText(
                        "As your training had already been made clear, reincarnation magic is hardly a precise science. \n\n" +
                        "If you remember your call sign and unit designation, please enter them below. " +
                        "Otherwise, please pick a new call sign and unit, and we'll refold you into the Organization."
                );
                break;
            case R.id.story_proceed2:
                // Record Username and Password

                setContentView(R.layout.story3);

                current = findViewById(R.id.story_text3);
                current.setText(
                        "To access your mission readiness, we need to know more about your current situation. \n\n" +
                        "The reincarnation magic should have picked an average family for you to ensure maximum likelihood that you reached maturation, " +
                        "and restored your memory at the appropriate time. \n\n" +
                        "Please confirm approximately how many years ago you arrived on Earth."
                );

                spinner = findViewById(R.id.age_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.ages, android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);
                break;
            case R.id.story_proceed3:
                setContentView(R.layout.story4);

                current = findViewById(R.id.story_text4);
                current.setText(
                        "Your number one priority is to keep your cover while operating on behind opposition lines. " +
                        "A hefty price was paid to send you to there. We will not lose you to Earth's surveillance state. \n\n" +
                        "Please choose the option that most accurately describe your situation, " +
                        "so that we can assign you the appropriate missions that does not conflict with your cover."
                );
                break;
            case R.id.working:
                // record and move to main screen
                finish();
                break;
            case R.id.training:
                // record and move to main screen
                finish();
                break;
            case R.id.other:
                // record and move to main screen
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Record age category, or deemed too young
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Warn that an age needed to be selected
    }
}