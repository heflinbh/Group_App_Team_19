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
    private Player p1;
    private Player p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_p1 = (TextView) findViewById(R.id.p1_score_text);
        score_p2 = (TextView) findViewById(R.id.p2_score_text);

        add_p1 = findViewById(R.id.p1_add_button);
        add_p2 = findViewById(R.id.p2_add_button);

        database = FirebaseDatabase.getInstance().getReference();

        p1 = null;
        p2 = null;

        database.child("players").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        PlayerRep p = snapshot.getValue(PlayerRep.class);
                        Log.d("ADDED PLAYER", p.getName());

                        if (snapshot.getKey().equalsIgnoreCase("player1")) {
                            score_p1.setText(String.valueOf(p.getScore()));
                            p1 = new Player("abc", 10);
                        } else {
                            score_p2.setText(String.valueOf(p.getScore()));
                            p2 = new Player("def", 5);
                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                        Player player = snapshot.getValue(Player.class);
//
//                        if (snapshot.getKey().equalsIgnoreCase("player1")) {
//                            score_p1.setText(String.valueOf(player.score));
//                        } else {
//                            score_p2.setText(String.valueOf(player.score));
//                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext()
                                , "DBError1: ", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        if (p1 == null) {
            Log.d("onCreate", "GOT HERE");
            database.child("players").child("player1").setValue(new PlayerRep("player 1", "0"));
            database.child("players").child("player2").setValue(new PlayerRep("player 2", "0"));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.p1_add_button:
                this.addPoints("p1");
                break;

            case R.id.p2_add_button:
                this.addPoints("p2");
                break;
        }
    }

//    // Add data to firebase button
//    public void doAddDataToDb(View view) {
//        // Write a message to the database
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");
//
//        Task t = myRef.setValue("Hello, World!");
//        if(!t.isSuccessful()){
//            Toast.makeText(getApplicationContext()
//                    , "Failed to write value into firebase. " , Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Read from the database by listening for a change to that item.
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//                Toast.makeText(getApplicationContext()
//                        , "Failed to write value into firebase. " , Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//    }

    private void addPoints(String player) {
        if (player == "p1") {
            p1.addPoint();
        } else {
            p2.addPoint();
        }
        
//        database
//                .child("player")
//                .child(player)
//                .runTransaction(new Transaction.Handler() {
//                    @Override
//                    public Transaction.Result doTransaction(MutableData mutableData) {
//
//                        Player player = mutableData.getValue(Player.class);
//                        if (player == null) {
//                            return Transaction.success(mutableData);
//                        }
//
//                        player.score = String.valueOf(Integer.valueOf(player.score) + 1);
//                        mutableData.setValue(player);
//
//                        return Transaction.success(mutableData);
//                    }
//
//                    @Override
//                    public void onComplete(DatabaseError databaseError, boolean b,
//                                           DataSnapshot dataSnapshot) {
//                        // Transaction completed
//                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
//                        Toast.makeText(getApplicationContext()
//                                , "DBError2: " + databaseError, Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}