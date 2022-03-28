package edu.neu.a7stickittoem_team19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.Date;

public class LoginSignup extends AppCompatActivity {
    private FbService fbService;
    private boolean bound;
    private EditText usernameEditText;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            FbService.LocalBinder binder = (FbService.LocalBinder) iBinder;
            fbService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_login_signup);

        Thread t = new Thread() {
            @Override
            public void run () {
                startService(new Intent(getApplicationContext(), FbService.class));
            }
        };
        t.start();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:

                notification();
                usernameEditText = findViewById(R.id.username);
                String username = usernameEditText.getText().toString();

                if (!FbService.containsUser(username)) {
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

                    User me = new User(username);
                    myRef.child(username).setValue(me);
                    FbService.setMe(me);
                }
                FbService.setTimestamp(createTimestamp());

                Intent sendActivity = new Intent(getApplicationContext(), Send.class);
                sendActivity.putExtra("Username", username);
                startActivity(sendActivity);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, FbService.class), connection, Context.BIND_AUTO_CREATE);
        bound = true;
        FbService.registerContext(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindService(connection);
        bound = false;
        FbService.registerContext(null);
    }

    private long createTimestamp() {
        Date date = new Date();
        Timestamp stamp = new Timestamp(date.getTime());
        return stamp.getTime();
    }

    private void createNotificationChannel() {

        // create channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("notification_channel", "notification_channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void notification() {
        // create notification
        Notification notification = new NotificationCompat.Builder(this, getString(R.string.channel_id))
                .setContentTitle("Logged in")
                .setContentText("You're logged in.")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification_icon)))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL ;

        notificationManager.notify(0, notification);
    }
}