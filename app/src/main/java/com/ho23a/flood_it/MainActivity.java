package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private BoardView boardView;

    private ColorScheme colorScheme;
    private Level level;

    private Board board;
    private int numRows;
    private int numSteps;
    private int initialNumSteps;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();
    private HashMap<Level, Integer> levelToNumSteps = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorScheme = Settings.getIntsance().getColorScheme();
        level = Settings.getIntsance().getLevel();

        ((Button) findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), WelcomeActivity.class);
                startActivity(myIntent);
            }
        });

        setup();
        reset();
    }

    private void reset() {
        // set values
        initialNumSteps = levelToNumSteps.get(level);
        numSteps = initialNumSteps;
        numRows = levelToSizeMap.get(level);
        board = new Board(numRows);

        stepsText = ((TextView) findViewById(R.id.stepsText));
        stepsText.setText(String.format(getString(R.string.steps_text), initialNumSteps, initialNumSteps));

        boardView = (BoardView) findViewById(R.id.board_view);

        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int touchedX = (int) event.getX();
                    int touchedY = (int) event.getY();
                    System.out.println("Touch coordinates : " +
                            String.valueOf(touchedX) + "x" + String.valueOf(touchedY));
                    updateGame(touchedX, touchedY);
                }
                return true;
            }
        });

        boardView.post(new Runnable() {
            @Override
            public void run() {
                initializeBoard(numRows, boardView.getWidth(), boardView.getHeight());
            }
        });

        resetBoardView();
    }

    private void initializeBoard(int numRows, int boardWidth, int boardHeight) {
        int tileSize = Math.min(boardWidth, boardHeight)/ numRows;

        for (int i = 0; i < numRows; i++) {
            int x = 0;
            int y = tileSize * i;
            for (int j = 0; j < numRows; j++) {
                board.setXY(i, j, x + tileSize * j, y, tileSize);
            }
        }

        boardView.setBoardSize(numRows);
        boardView.setTileSize(tileSize);
    }

    private void updateGame(int touchedX, int touchedY) {
        boolean updated = updateBoard(touchedX, touchedY);

        if (updated) {
            // continue game, update boardView and stepsText
            updateStepsText();
            resetBoardView();
        }

        checkForWin(board.checkWon());
    }

    public static final String WIN_NUM_STEPS = "com.ho23a.flood_it.WIN_NUM_STEPS";
    public static final String GAME_WON = "com.ho23a.flood_it.GAME_WON";

    private void checkForWin(boolean isFilled) {
        if (numSteps >= 0  & isFilled) {
            // won
            int stepsUsed = initialNumSteps - numSteps;
            String winNumSteps = String.format("%d/%d", stepsUsed, initialNumSteps);

            Intent intent = new Intent (this, GameOverActivity.class);
            intent.putExtra(WIN_NUM_STEPS, winNumSteps);
            intent.putExtra(GAME_WON, true);
            startActivity(intent);

        } else if (numSteps == 0) {
            // lose
            Intent intent = new Intent (this, GameOverActivity.class);
            intent.putExtra(GAME_WON, false);
            startActivity(intent);
        }
    }

    private boolean updateBoard(int touchedX, int touchedY) {
        Tile clickedTile = board.getClickedTile(touchedX, touchedY);

        if (clickedTile != null) {
//            System.out.println(String.format("clicked [%d, %d]", clickedTile.getX(), clickedTile.getY()));
            return board.updateBoard(clickedTile.getColor());
        }
        // did not update board
        return false;
    }

    private void resetBoardView() {
        boardView.reset(board.getBoard());
    }

    private void setup() {
        //initialize level to grid size HM
        levelToSizeMap.put(Level.DEFAULT, 2);
        levelToSizeMap.put(Level.EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);

        //initialize level to numSteps HM
        levelToNumSteps.put(Level.DEFAULT, 4);
        levelToNumSteps.put(Level.EASY, 4);
        levelToNumSteps.put(Level.MEDIUM, 12);
        levelToNumSteps.put(Level.HARD, 16);
    }

    /**
     * Update number of steps on click
     */
    private void updateStepsText() {
        numSteps -= 1;
        stepsText.setText(String.format(getString(R.string.steps_text), numSteps, initialNumSteps));
    }
}
