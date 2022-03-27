package edu.neu.a7stickittoem_team19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginSignup extends AppCompatActivity {
    private FbService fbService;
    private boolean bound;
    private String username; // need to be saved/passed on
    private EditText usernameEditText;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            FbService.LocalBinder binder = (FbService.LocalBinder) iBinder;
            fbService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Thread t = new Thread() {
            @Override
            public void run () {
                startService(new Intent(getApplicationContext(), FbService.class));
            }
        };
        t.run();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                Intent sendActivity = new Intent(getApplicationContext(), Send.class);

                usernameEditText = findViewById(R.id.username);
                String username = usernameEditText.getText().toString();
                sendActivity.putExtra("Username", username);

                if (!FbService.containsUser(username)) {
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

                    User me = new User(username);
                    myRef.child(username).setValue(me);
                    FbService.setMe(me);
                }

                startActivity(sendActivity);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, FbService.class), connection, Context.BIND_AUTO_CREATE);
        bound = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindService(connection);
        bound = false;
    }
}