package com.ho23a.flood_it;

import android.graphics.Color;

import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static com.ho23a.flood_it.Level.EASY;
import static com.ho23a.flood_it.Level.HARD;
import static com.ho23a.flood_it.Level.MEDIUM;

/**
 * Created by demouser on 1/17/17.
 */

public class Board {
    private int size;
    /* private enum Level{
         EASY,
         MEDIUM,
         HARD };*/
    private Tile board[][];
    private int numColors;
    private Random random;


   /*public enum Color

    {
        RED,
        GREEN
    }*/

    /* constructor */
    public Board(Level level) {
        createBoard(level);
    }

    public Board() {

    }

    /**
     * initializes board and its components
     */
    public void createBoard(Level level) {

        //determine boards size based on level
        switch (level) {
            case EASY:
                size = 2;
                numColors = 2;
                break;
            case MEDIUM:
                size = 4;
                numColors = 4;

                break;
            case HARD:
                size = 8;
                numColors = 8;

                break;
            default:
                size = 2;
                numColors = 2;

                break;

        }

        board = new Tile[size][size];
        floodFill();
    }

    /**
     * fill board with random colors
     */
    private void floodFill() {

        if (numColors == 2) {
            int colors[] = new int[]{Color.RED, Color.BLUE};
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    board[r][c] = new Tile();
                     random = new Random();
                       int randomNum = random.nextInt(2);

                    board[r][c].setColor(colors[randomNum]);

                }
            }


        }

    }


    /**
     * Prints board with the color of each tile
     * @return
     */
    public int[][] printBoard() {
        int [][] array = new int[size][size];
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                System.out.println(array[r][c] = (board[r][c].getColor()));
            }
        }
        return array;

    }

    public boolean checkWon(){
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[0][0].getColor() != board[r][c].getColor()) {
                    return false;
                }
            }
        }
        return true;

    }

    public void setLevel(String level){
        switch(level){
            case "EASY":

        }
    }
}
