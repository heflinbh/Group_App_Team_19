package edu.neu.theworstgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity {

    private String callSign;

    private ArrayList<User> friendsList;
    private RecyclerView socialRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        Bundle intent = getIntent().getExtras();
        callSign = intent.getString("Call Sign");
        TextView socialTextView = findViewById(R.id.socialWelcomeTextView);
        socialTextView.setText("Showing Social for " + callSign);

        socialRecyclerView = findViewById(R.id.socialRecyclerView);

        friendsList = new ArrayList<>();
        setFriendList();
        setAdapter();
    }

    private void setAdapter() {
        SocialRecyclerAdapter adapter = new SocialRecyclerAdapter(friendsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        socialRecyclerView.setLayoutManager(layoutManager);
        socialRecyclerView.setItemAnimator(new DefaultItemAnimator());
        socialRecyclerView.setAdapter(adapter);
    }

    private void setFriendList() {
        // TODO
        // Get Friend List from Database

        // Sample users added below
        friendsList.add(new User("Benjamin", "Novice"));
        friendsList.add(new User("Yuan", "Apprentice"));
        friendsList.add(new User("Chenyang", "Veteran"));
        friendsList.add(new User("Vandita", "Expert"));

        friendsList.add(new User("One", "Novice"));
        friendsList.add(new User("Two", "Apprentice"));
        friendsList.add(new User("Three", "Veteran"));
        friendsList.add(new User("Four", "Expert"));
        friendsList.add(new User("Five", "Novice"));
        friendsList.add(new User("Six", "Apprentice"));
        friendsList.add(new User("Seven", "Veteran"));
        friendsList.add(new User("Eight", "Expert"));
    }
}