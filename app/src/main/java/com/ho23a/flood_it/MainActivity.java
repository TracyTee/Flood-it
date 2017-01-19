package com.ho23a.flood_it;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private BoardView boardView;

    private ColorScheme colorScheme;
    private Level level = Level.DEFAULT;
    private Board board;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Settings settings = intent.getParcelableExtra(SettingsActivity.SETTINGS_LABEL);

        levelToSizeMap.put(Level.DEFAULT, 2);
        levelToSizeMap.put(Level.EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);

        colorScheme = settings.getColorScheme();
        level = settings.getLevel();
        board = new Board(levelToSizeMap.get(level));

        // set steps to stepstext
        stepsText = ((TextView) findViewById(R.id.stepsText));
        boardView = (BoardView) findViewById(R.id.board_view);
        resetBoardView();

    }

    private void resetBoardView() {
        boardView.reset(board);
    }
}
