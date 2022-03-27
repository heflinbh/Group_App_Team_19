package edu.neu.a7stickittoem_team19;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FbService extends Service {
    private final IBinder binder = new LocalBinder();
    private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private final ArrayList<Message> messages = new ArrayList<>();
    private static ArrayList<String> users = new ArrayList<>();
    private ReadWriteLock messagesRWLock = new ReentrantReadWriteLock();
    private ReadWriteLock usersRWLock = new ReentrantReadWriteLock();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Return reference to the service to activities that binds to it
    public class LocalBinder extends Binder {
        FbService getService() {
            return FbService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dbRef.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // get the relevant write lock
                // add the new information in the user list
                // release write lock
                // send appropriate notifications as needed
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw new IllegalStateException("should never reach this state");
            }
        });

        dbRef.child("Inbox").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // acquire the appropriate write lock
                // add information in the messages list
                // release write lock
                // send appropriate notifications as needed
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                throw new IllegalStateException("should never reach this state");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw new IllegalStateException("should never reach this state");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    // methods for clients
    public User getNthUser() {
        // acquire read lock
        // pull appropriate information
        // release read lock
        throw new UnsupportedOperationException("Not Implemented");
    }

    public Message getNthMessage() {
        // acquire read lock
        // pull appropriate information
        // release read lock
        throw new UnsupportedOperationException("Not Implemented");
    }

    // Get number of users/messages
    // Get latest changes

    public static Boolean containsUser(String username) {
        return users.contains(username);
    }
}