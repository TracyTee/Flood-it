package com.ho23a.flood_it;

/**
 * Created by demouser on 1/18/17.
 */

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static android.graphics.Color.BLUE;

/**
 * Test for Board class
 */
public class BoardTest {

    Board boardClass= new Board(4);

   // @Before
    public void setupBoard(){
        Board boardClass = new Board(4);

    }

    @Test
    public void printBoard(){
        //System.out.println(Arrays.toString(boardClass.printBoard()));
    }

    @Test
    public void updateBoardTest(){
//        System.out.println(Arrays.toString(boardClass.printBoard()));
        boardClass.updateBoard(Color.RED);
//        System.out.println(Arrays.toString(boardClass.printBoard()));
        //System.out.print(Color.BLUE);
    }


}
