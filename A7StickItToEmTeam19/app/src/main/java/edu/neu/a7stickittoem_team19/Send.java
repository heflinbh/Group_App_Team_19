package edu.neu.a7stickittoem_team19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class Send extends AppCompatActivity {

    private TextInputEditText recipientInput;
    private ImageButton smileyButton;
    private ImageButton upsideDownButton;
    private Button history;
    private Drawable smileySticker;
    private Drawable upsideDownSticker;
    private Drawable stickerToSend;
    private String recipientUser;

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


}