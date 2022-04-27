package edu.neu.theworstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public User user;

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
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");

        switch (view.getId()) {
            case R.id.loginButton:
                Intent mainScreenActivity = new Intent(getApplicationContext(), HomeActivity.class);
                mainScreenActivity.putExtra("Call Sign", callSign);
                mainScreenActivity.putExtra("Unit Designation", unitDesignation);

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(callSign)) {
                            myRef.child(callSign).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    user = snapshot.getValue(User.class);
                                    if (!unitDesignation.equals(user.getPassword())){
                                        Toast.makeText(LoginActivity.this, "Password incorrect.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();

                                        mainScreenActivity.putExtra("user", user);
                                        startActivity(mainScreenActivity);
                                    };
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Toast.makeText(LoginActivity.this, "Username does not exist.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                break;

            case R.id.signUpButton:
                Intent storyStart = new Intent(getApplicationContext(), StoryStart.class);
                storyStart.putExtra("Call Sign", callSign);
                storyStart.putExtra("Unit Designation", unitDesignation);

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(callSign)){
                            Toast.makeText( LoginActivity.this, "Username existed. Please login.", Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(storyStart);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                break;
        }
    }


    /**
     * create initial user and add to firebase
     * @param username
     * @param display_name
     * @param password
     */
    public void createNewUser(String username, String display_name, String password) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        User user = new User(username
                , password
        );
        user.setDisplayName(display_name);
        myRef.child(username).setValue(user);

        Toast.makeText( this, "User created.", Toast.LENGTH_SHORT).show();
    }

}