package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MissionAlphaActivity extends AppCompatActivity {

    public int counter = 500;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_alpha);
        textView= findViewById(R.id.textView);

        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish(){
                textView.setText("FINISH!!");
            }
        }.start();
    }
}