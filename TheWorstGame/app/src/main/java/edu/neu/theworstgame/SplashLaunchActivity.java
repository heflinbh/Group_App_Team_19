package edu.neu.theworstgame;

import static edu.neu.theworstgame.R.drawable.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;

public class SplashLaunchActivity extends AppCompatActivity {
    private final String FILE_NAME = "AGENT.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_launch);

        getSupportActionBar().hide();

        int base = 300;

        // Determine if this is the first time playing the game
        File file = getApplicationContext().getFileStreamPath(FILE_NAME);
        boolean first_time = !file.exists();

        ImageView iv = findViewById(R.id.imageView);
        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(neptune);
            }
        }, 1 * base);

        Handler h2 = new Handler();
        h2.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(neptune);
            }
        }, 2 * base);

        Handler h3 = new Handler();
        h3.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(mercury);
            }
        }, 3 * base);

        Handler h4 = new Handler();
        h4.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(aries);
            }
        }, 4 * base);

        Handler h5 = new Handler();
        h5.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(earth);
            }
        }, 5 * base);

        Handler h6 = new Handler();
        h6.postDelayed(new Runnable() {
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
        }, 6 * base);

        // Start background service in an separate thread
    }
}