package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:

                TextInputEditText textInput = findViewById(R.id.callSignTextInput);
                String callSign = textInput.getText().toString();

                textInput = findViewById(R.id.unitDesignationTextInput);
                String unitDesignation = textInput.getText().toString();

                Intent mainScreenActivity = new Intent(getApplicationContext(), MainActivity.class);
                mainScreenActivity.putExtra("Call Sign", callSign);
                mainScreenActivity.putExtra("Unit Designation", unitDesignation);

                startActivity(mainScreenActivity);

                break;

            case R.id.signUpButton:

                break;
        }
    }
}