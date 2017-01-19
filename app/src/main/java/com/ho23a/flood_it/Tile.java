package com.ho23a.flood_it;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

/**
 * Created by demouser on 1/17/17.
 */

public class Tile {

    private static int x; //x coordinates
    private static int y; //y coordinates
    private int color;

    public Paint p;

    /* CONSTRUCTOR */
    public Tile(){
        //color = Color.BLUE ;
    }

    /**
     *
     * @return color of tile
     */
    public int getColor(){
        return this.color;
    }

    /**
     * Sets tile color to c
     * @param c
     */
    public void setColor(int c){

    this.color =c;
    }

    /**
     *
     * @return x coordinate of tile
     */
    public int getX(){
        return this.x;
    }

    /**
     *
     * @return y coordinate of tile
     */
    public int getY(){
        return this.y;
    }




}
