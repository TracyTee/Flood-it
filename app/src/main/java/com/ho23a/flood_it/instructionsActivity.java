package com.ho23a.flood_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class instructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);


        ((TextView) findViewById(R.id.instructionsText)).setText("Welcome to Flood it game.\n " +
                "Goal: Turn the entire grid into one color in the specified number of steps.\n " +
                "Your guiding key is the top left corner tile. \n" +
                "Select a color from the choices on the bottom of the screen.\n" +
                " This will change continuous tiles of the same color as the corner tile to the specified color.\n");
    }
}
