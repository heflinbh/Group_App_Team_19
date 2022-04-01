package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        TextInputLayout textInput = (TextInputLayout) findViewById(R.id.callSignTextInput);
        String callSign = textInput.getEditText().getText().toString();

        textInput = (TextInputLayout) findViewById(R.id.unitDesignationTextInput);
        String unitDesignation = textInput.getEditText().getText().toString();

        switch (view.getId()) {
            case R.id.loginButton:
                Intent mainScreenActivity = new Intent(getApplicationContext(), HomeActivity.class);
                mainScreenActivity.putExtra("Call Sign", callSign);
                mainScreenActivity.putExtra("Unit Designation", unitDesignation);
                startActivity(mainScreenActivity);
                break;

            case R.id.signUpButton:
                Intent storyActivity = new Intent(getApplicationContext(), StoryActivity.class);
                storyActivity.putExtra("Call Sign", callSign);
                storyActivity.putExtra("Unit Designation", unitDesignation);
                startActivity(storyActivity);
                break;
        }
    }
}