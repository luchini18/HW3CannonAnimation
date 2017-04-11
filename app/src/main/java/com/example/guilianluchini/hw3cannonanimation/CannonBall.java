package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by guilianluchini on 3/28/17.
 */
/**
 External Citation
 Date:     1 April 2017
 Problem:  creating a ball that moves across the screen
 Resource: Moodle/Nuxoll
 Solution: I used small portions of ball class
 */
/**
 External Citation
 Date:     7 April 2017
 Problem:  how to tell if cannonball and target are overlapping
 Resource: Moodle/Nuxoll
 Solution: Doodads spot demo had the method overlaps() that I used
 */

public class CannonBall {
    //instance variables
    private int x, y;
    private float size;
    private double velX;
    private double velY;
    private Paint myPaint = new Paint();

    //constructor
    public CannonBall(int initX, int initY) {
        x = initX;
        y = initY;
        size = 25;
        myPaint.setColor(Color.BLACK);
    }

    /**
     * accessor methods
     */
    public float getSize() {
        return size;
    }

    public Paint getMyPaint() {
        return myPaint;
    }

    public void drawMe(Canvas canvas) {
        canvas.drawCircle(x, y, size, myPaint);
    }

    public boolean overlaps(Target other)
    {
        //Determine the distance between the two spots
        float xDist = Math.abs (other.x - this.x);
        float yDist = Math.abs (other.y - this.y);
        float dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);

        //which spot is largest?
        float largeSize = Math.max(this.getSize(), other.getSize());

        return dist <= largeSize;
    }

}

