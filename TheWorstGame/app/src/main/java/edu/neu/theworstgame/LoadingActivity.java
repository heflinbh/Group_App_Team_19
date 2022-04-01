package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    ImageView imageView;
    Button rotateBTN, stopBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = findViewById(R.id.imageviewTest);
        rotateBTN = findViewById(R.id.BTNrotate);
        stopBTN = findViewById(R.id.BTNstop);

    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.BTNrotate:
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
                imageView.startAnimation(animation);
                break;

            case R.id.BTNstop:
                imageView.clearAnimation();
                break;


        }
    }
}