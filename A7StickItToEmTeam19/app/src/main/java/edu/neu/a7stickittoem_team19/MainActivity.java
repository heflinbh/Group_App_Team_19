package edu.neu.a7stickittoem_team19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import edu.neu.a7stickittoem_team19.Player;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference database;
    private TextView score_p1;
    private TextView score_p2;
    private Button add_p1;
    private Button add_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_p1 = (TextView) findViewById(R.id.p1_score_text);
        score_p2 = (TextView) findViewById(R.id.p2_score_text);

        add_p1 = findViewById(R.id.p1_add_button);
        add_p2 = findViewById(R.id.p2_add_button);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("players").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Player player = snapshot.getValue(Player.class);

                        if (snapshot.getKey().equalsIgnoreCase("player1")) {
                            score_p1.setText(String.valueOf(player.score));
                        } else {
                            score_p2.setText(String.valueOf(player.score));
                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Player player = snapshot.getValue(Player.class);

                        if (snapshot.getKey().equalsIgnoreCase("player1")) {
                            score_p1.setText(String.valueOf(player.score));
                        } else {
                            score_p2.setText(String.valueOf(player.score));
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext()
                                , "DBError: ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.p1_add_button:
                this.addPoints(database,"p1");
                break;

            case R.id.p2_add_button:
                this.addPoints(database,"p2");
                break;
        }
    }

    // Add data to firebase button
    public void doAddDataToDb(View view) {
        // Write a message to the database
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");

        Task t = myRef.setValue("Hello, World!");
        if(!t.isSuccessful()){
            Toast.makeText(getApplicationContext()
                    , "Failed to write value into firebase. " , Toast.LENGTH_SHORT).show();
            return;
        }

        // Read from the database by listening for a change to that item.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getApplicationContext()
                        , "Failed to write value into firebase. " , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void addPoints(DatabaseReference postRef, String player) {
        postRef
                .child("player")
                .child(player)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {

                        Player player = mutableData.getValue(Player.class);
                        if (player == null) {
                            return Transaction.success(mutableData);
                        }

                        player.score = String.valueOf(Integer.valueOf(player.score) + 1);
                        mutableData.setValue(player);

                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b,
                                           DataSnapshot dataSnapshot) {
                        // Transaction completed
                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                        Toast.makeText(getApplicationContext()
                                , "DBError: " + databaseError, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}