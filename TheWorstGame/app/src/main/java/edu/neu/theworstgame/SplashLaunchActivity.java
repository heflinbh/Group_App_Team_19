package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_launch);

        getSupportActionBar().hide();

        // Determine if this is the first time playing the game
        boolean first_time = true;

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (first_time) {
                    i = new Intent(getApplicationContext(), StoryStart.class);
                } else {
                    i = new Intent(getApplicationContext(), LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 2000);

        // Start background service in an separate thread
    }
}