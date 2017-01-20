package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        boolean isWon = intent.getBooleanExtra(MainActivity.GAME_WON, false);

        TextView gameOverText = (TextView) findViewById(R.id.gameOverText);
        if (isWon) {
            String winNumSteps = intent.getStringExtra(MainActivity.WIN_NUM_STEPS);
            gameOverText.setText(String.format(getString(R.string.won_text), winNumSteps));
        } else {
            gameOverText.setText(R.string.lost_text);
        }

        ((Button) findViewById(R.id.replayButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });

        ((Button) findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WelcomeActivity.class));
            }
        });
    }
}
