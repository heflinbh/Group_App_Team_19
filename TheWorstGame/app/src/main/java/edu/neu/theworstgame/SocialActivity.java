package edu.neu.theworstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class SocialActivity extends AppCompatActivity {

    private String callSign;
    private User thisUser;

    private ArrayList<User> userArrayList;
    private RecyclerView socialRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("users");

        Bundle intent = getIntent().getExtras();
        if (intent != null){
            callSign = intent.getString("Call Sign");
            thisUser = (User) intent.getSerializable("user");
        } else {
            thisUser = new User();
        }

        TextView socialTextView = findViewById(R.id.socialWelcomeTextView);
        socialTextView.setText("Showing Leaderboard");

        socialRecyclerView = findViewById(R.id.socialRecyclerView);

        userArrayList = new ArrayList<User>();
//        setFriendList();
        userArrayList.add(thisUser);
        setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("users");
        setLeaderboard(myRef);
        setAdapter();
    }

    private void setAdapter() {
        SocialRecyclerAdapter adapter = new SocialRecyclerAdapter(userArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        socialRecyclerView.setLayoutManager(layoutManager);
        socialRecyclerView.setItemAnimator(new DefaultItemAnimator());
        socialRecyclerView.setAdapter(adapter);
    }

    private void setFriendList() {
        // TODO
        // Get Friend List from Database

        // Sample users added below
//        userArrayList.add(new User("Benjamin", "Novice"));
//        userArrayList.add(new User("Yuan", "Apprentice"));
//        userArrayList.add(new User("Chenyang", "Veteran","display", -1,4,100));
//        userArrayList.add(new User("Vandita", "Expert"));
//
//        userArrayList.add(new User("One", "Novice"));
//        userArrayList.add(new User("Two", "Apprentice"));
//        userArrayList.add(new User("Three", "Veteran"));
//        userArrayList.add(new User("Four", "Expert"));
//        userArrayList.add(new User("Five", "Novice"));
//        userArrayList.add(new User("Six", "Apprentice"));
//        userArrayList.add(new User("Seven", "Veteran"));
//        userArrayList.add(new User("Eight", "Expert"));
    }

    private void setLeaderboard(DatabaseReference myRef) {

        Query query = myRef.orderByChild("points").limitToLast(10);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot shot:snapshot.getChildren()) {
                    User user;
                    user = shot.getValue(User.class);
                    userArrayList.add(user);
                }
                Collections.reverse(userArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SocialActivity.this, "getting user list failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}