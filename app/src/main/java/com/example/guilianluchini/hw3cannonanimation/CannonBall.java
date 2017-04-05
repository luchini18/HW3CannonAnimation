package com.example.guilianluchini.hw3cannonanimation;

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

public class CannonBall {
    //instance variables
    private final double INITIAL_VELOCITY = 30;

    private float size;
    private double velX;
    private double velY;
    private Paint myPaint = new Paint();

    //constructor
    public CannonBall(double initX, double initY)
    {
        size = 25;
        myPaint.setColor(Color.BLACK);
        velX = INITIAL_VELOCITY;
        velY = INITIAL_VELOCITY;
    }

    /** accessor methods */
    public double getXVel() { return velX; }
    public double getYVel() { return velY; }
    public float getSize() { return size; }
    public Paint getMyPaint() {return myPaint;}
}

