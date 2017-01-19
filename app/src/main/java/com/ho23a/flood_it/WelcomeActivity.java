package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //setup buttons
        ((Button) findViewById(R.id.playButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });


        ((Button) findViewById(R.id.instructionsButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInstructions();
            }
        });


        ((Button) findViewById(R.id.settingsButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSettings();
            }
        });
    }

    public void play(){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void startInstructions(){
        //open instructions display page
        Intent intent = new Intent(this, instructionsActivity.class);
        startActivity(intent);
    }

    public void startSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
