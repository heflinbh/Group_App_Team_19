package edu.neu.theworstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StoryStart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView current1;
    private TextView current2;
    private Spinner spinner;

    private String username;
    private String password;

    private User user;

    private final String FILE_NAME = "AGENT.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_start);

        current1 = findViewById(R.id.story_text1);
        current1.setText(
                "Apologies for the \"forget me\" spell. We operate in an hostile world. \n\n" +
                "You are here, so I assume you have regained enough of your previous memories to remember this channel. \n"
        );
    }

    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.story_proceed1:
                setContentView(R.layout.story2);
                current1 = findViewById(R.id.story_text2_1);
                current1.setText(
                        "As your training had already been made clear, reincarnation magic is hardly a precise science. \n"
                );

                current2 = findViewById(R.id.story_text2_2);
                current2.setText(
                        "If you remember your call sign and pass phrase, please enter them below. " +
                        "Otherwise, please pick a new call sign and pass phrase, and we'll refold you into the Organization."
                );

                break;
            case R.id.story_proceed2:
                // Record Username and Password

                TextInputLayout callSign = findViewById(R.id.call_sign_input);
                TextInputLayout unitDesignation = findViewById(R.id.unit_designation_input);

                username = callSign.getEditText().getText().toString();
                password = unitDesignation.getEditText().getText().toString();

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(username)){

                            myRef.child(username).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    user = snapshot.getValue(User.class);

                                    if (!password.equals(user.getPassword())){
                                        Toast.makeText(StoryStart.this, "Username exist. Please change another call sign.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(StoryStart.this, "Logged in.", Toast.LENGTH_SHORT).show();

                                        Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                        homeActivityIntent.putExtra("Call Sign", username);
                                        homeActivityIntent.putExtra("Unit Designation", password);
                                        homeActivityIntent.putExtra("user", user);
                                        startActivity(homeActivityIntent);
                                    };
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            User user = new User(username
                                    , password
                            );
                            myRef.child(username).setValue(user);

                            Toast.makeText(StoryStart.this, "User created", Toast.LENGTH_SHORT).show();

                            setContentView(R.layout.story3);

                            current1 = findViewById(R.id.story_text3);
                            current1.setText(
                                    "To access your mission readiness, we need to know more about your current situation. \n\n" +
                                            "The reincarnation magic should have picked an average family for you to ensure maximum likelihood that you reached maturation, " +
                                            "and restored your memory at the appropriate time. \n\n" +
                                            "Please confirm approximately how many years ago you arrived on Earth."
                            );

                            spinner = findViewById(R.id.age_spinner);
                            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(StoryStart.this,
                                    R.array.ages, android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(StoryStart.this);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


//                setContentView(R.layout.story3);
//
//                current1 = findViewById(R.id.story_text3);
//                current1.setText(
//                        "To access your mission readiness, we need to know more about your current situation. \n\n" +
//                        "The reincarnation magic should have picked an average family for you to ensure maximum likelihood that you reached maturation, " +
//                        "and restored your memory at the appropriate time. \n\n" +
//                        "Please confirm approximately how many years ago you arrived on Earth."
//                );
//
//                spinner = findViewById(R.id.age_spinner);
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                        R.array.ages, android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(adapter);
//                spinner.setOnItemSelectedListener(this);
                break;
            case R.id.story_proceed3:
                setContentView(R.layout.story4);

                current1 = findViewById(R.id.story_text4);
                current1.setText(
                        "Your number one priority is to keep your cover while operating on behind opposition lines. " +
                        "A hefty price was paid to send you to there. We will not lose you to Earth's surveillance state. \n\n" +
                        "Please choose the option that most accurately describe your situation, " +
                        "so that we can assign you the appropriate missions that does not conflict with your cover."
                );
                break;
            case R.id.working:
                // record and move to main screen
                Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);

                homeActivityIntent.putExtra("Call Sign", username);
                homeActivityIntent.putExtra("Unit Designation", password);
                user = new User(username
                        , password
                );
                homeActivityIntent.putExtra("user", user);

                File file = getApplicationContext().getFileStreamPath(FILE_NAME);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write("".getBytes());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(homeActivityIntent);
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