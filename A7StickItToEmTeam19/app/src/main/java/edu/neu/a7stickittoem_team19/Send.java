package edu.neu.a7stickittoem_team19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Send extends AppCompatActivity {
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

    private TextInputEditText recipientInput;
    private ImageButton smileyButton;
    private ImageButton upsideDownButton;
    private Button history;
    private Drawable smileySticker;
    private Drawable upsideDownSticker;
    private Drawable stickerToSend;
    private String recipientUser;
    private String fromUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send);

        recipientInput = (TextInputEditText) findViewById(R.id.recipientInput);
        smileyButton = (ImageButton) findViewById(R.id.smileyButton);
        upsideDownButton = (ImageButton) findViewById(R.id.upsideDownButton);
        history = (Button) findViewById(R.id.history);
        smileySticker = ResourcesCompat.getDrawable(getResources(), R.drawable.smiley, null);
        upsideDownSticker = ResourcesCompat.getDrawable(getResources(), R.drawable.upsidedown, null);

        Intent intent = getIntent();
        fromUsername = intent.getStringExtra("Username");

        smileyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerToSend = smileySticker;
                recipientUser = recipientInput.getText().toString();
                //Would need to add logic for if username is not found in the database here
                if (recipientUser.length() == 0) {
                    //this just checks if there's no username input
                    Toast.makeText(Send.this, "Enter a username to send the sticker to first!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Send.this, String.format("Sending smiley sticker to %s", recipientUser),
                            Toast.LENGTH_SHORT).show();
                    //Add logic to send to the inputted user here
                    sendSticker(recipientUser, 1);
                }

            }
        });

        upsideDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerToSend = upsideDownSticker;
                recipientUser = recipientInput.getText().toString();
                //Would need to add logic for if username is not found in the database here
                if (recipientUser.length() == 0) {
                    Toast.makeText(Send.this, "Enter a username to send the sticker to first!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Send.this, String.format("Sending upside down sticker to %s", recipientUser),
                            Toast.LENGTH_SHORT).show();
                    //Add logic to send to the inputted user here
                    sendSticker(recipientUser, 2);
                }

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Send.this, History.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, FbService.class), connection, Context.BIND_AUTO_CREATE);
        bound = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindService(connection);
        bound = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        FbService.registerContext(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FbService.registerContext(null);
    }

    public void sendSticker(String recipient, Integer stickerId) {

        Date timestamp = Calendar.getInstance().getTime();
        String refStr = "Inbox";

        Message message = new Message(fromUsername, recipient, String.valueOf(stickerId));

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(refStr);
        myRef.push().setValue(message);

        if (stickerId == 1) {
            FbService.incrementStampASent();
        } else {
            FbService.incrementStampBSent();
        }
    }
}