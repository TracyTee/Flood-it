package com.ho23a.flood_it;

import static com.ho23a.flood_it.Level.EASY;
import static com.ho23a.flood_it.Level.HARD;
import static com.ho23a.flood_it.Level.MEDIUM;

/**
 * Created by demouser on 1/17/17.
 */

public class Board {
    private int size;
    private Tile tiles[][];

    /* constructor */
    public Board(){

    }

    /**
     * initializes board and its components
     */
    public void createBoard(Level level){

        //determine boards size based on level
        switch(level){
            case EASY:
                size = 2 ;
                break;
            case MEDIUM:
                size = 4;
                break;
            case HARD:
                size = 8;
                break;
            default:
                size = 2;
                break;

        }

        tiles = new Tile[size][size];
    }




}
