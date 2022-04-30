package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DebriefActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debrief);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.getSerializable("user");
        }

        TextView overview = findViewById(R.id.debrief_overview);
        TextView content = findViewById(R.id.debrief_content);

        overview.setText("Good Job on Completing the Mission\n\n");
        content.setText(
                "Your effort will be invaluable to our efforts to understand more about Earth, and to our long term objectives.\n\n" +
                "Until your next mission, remember that maintain your cover is in itself a full time job. " +
                "Now that you are reincarnated into a human body, please remember that your new body requires regular nutrition and water intake, " +
                "as well as, requires regular rest and repair. Maintaining your position in society will help provide important camouflage."
        );
    }

    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("user", user);
        startActivity(i);
        finish();
    }
}
