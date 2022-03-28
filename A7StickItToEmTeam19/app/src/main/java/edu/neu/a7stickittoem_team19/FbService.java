package edu.neu.a7stickittoem_team19;

import android.app.Service;
import android.content.Context;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FbService extends Service {
    private final IBinder binder = new LocalBinder();
    private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Message> messages = new ArrayList<>();
    private static final ArrayList<Message> myMessages = new ArrayList<>();

    private static User me = null;
    private static long timestamp = -1;

    private static Context uiContext = null;

    private ReadWriteLock usersRWLock = new ReentrantReadWriteLock();
    private ReadWriteLock messagesRWLock = new ReentrantReadWriteLock();
    private ReadWriteLock myMessagesRWLock = new ReentrantReadWriteLock();

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
                usersRWLock.writeLock().lock();
                User user = snapshot.getValue(User.class);
                users.add(user);
                usersRWLock.writeLock().unlock();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        dbRef.child("Inbox").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                messagesRWLock.writeLock().lock();
                Message message = snapshot.getValue(Message.class);
                messages.add(message);
                messagesRWLock.writeLock().unlock();

                if (message.getReceiver().equals(me)) {
                    myMessagesRWLock.writeLock().lock();
                    myMessages.add(message);
                    myMessagesRWLock.writeLock().unlock();

                    if (Long.valueOf(message.getTimestamp()) > timestamp) {
                        if (uiContext == null) {
                            // App not in foreground, send notification
                        } else {
                            // App in foreground, send toast message
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    // methods for clients
    public static Boolean containsUser(String username) {
        for (int i = 0; i < users.size(); ++i) {
            User tmp = users.get(i);
            if (tmp.getUsername() == username) {
                me = tmp;
                return true;
            }
        }
        return false;
    }

    public static void setMe(User newMe) {
        me = newMe;
    }

    public static void incrementStampASent() {
        me.incrementA();
    }

    public static void incrementStampBSent() {
        me.incrementB();
    }

    public static int getStickersSent() {
        return me.getStickersSent();
    }

    public static void setTimestamp(long ts) {
        timestamp = ts;
    }

    public static void registerContext(Context context) {
        uiContext = context;
    }

    // Private methods
    private String createTimestamp() {
        Date date = new Date();
        Timestamp stamp = new Timestamp(date.getTime());
        return String.valueOf(stamp.getTime());
    }
}