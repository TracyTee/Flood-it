package com.ho23a.flood_it;

public class Tile {

    private int x; //x coordinates
    private int y; //y coordinates
    private int color;
    private int size; //tile size

    /* CONSTRUCTOR */
    public Tile(){}

    public int getColor(){
        return this.color;
    }

    public int getSize() { return size; }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(int c){ this.color =c; }
}
