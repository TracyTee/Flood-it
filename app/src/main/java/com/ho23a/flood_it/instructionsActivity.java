package com.ho23a.flood_it;

import android.content.Intent;
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

        TextView instruction = ((TextView) findViewById(R.id.instructionsText));
        instruction.setText("Welcome to Flood-It! game.\n\n" +
                "Goal: Turn the entire grid into one color in the specified number of steps.\n" +
                "1. Your guiding key is the top left corner tile.\n" +
                "2. Select a color from the choices on the bottom of the screen.\n" +
                "3. This will change continuous tiles of the same color as the corner tile to the specified color.\n");

//        instruction.setTextSize(25);

        ((Button) findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMenu();
            }
        });
    }

    private void startMenu() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
