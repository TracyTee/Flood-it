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
                instructions();
            }
        });


        ((Button) findViewById(R.id.settingsButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void play(){

    }

    public void instructions(){
        //open instructions display page
        Intent intent = new Intent(this, instructionsActivity.class);
        startActivity(intent);
    }
}
