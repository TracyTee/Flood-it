package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        boolean isWon = intent.getBooleanExtra(MainActivity.GAME_WON, false);

        TextView gameOverText = (TextView) findViewById(R.id.gameOverText);
        String displayText;
        if (isWon == true) {
            String winNumSteps = intent.getStringExtra(MainActivity.WIN_NUM_STEPS);
            displayText = String.format("You won using %d steps", winNumSteps);
        } else {
            displayText = "Sorry you lost";
        }
        gameOverText.setText(displayText);
    }

    public void replay(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void startMenu(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
