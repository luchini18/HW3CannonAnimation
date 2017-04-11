package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by guilianluchini on 4/5/17.
 */

public class Target {
    //instance variables
    private final double INITIAL_VELOCITY = 30;

    private float size;
    private double velX;
    private double velY;
    protected int x,y,radiusA,radiusB,radiusC;
    private Paint red = new Paint();
    private Paint white = new Paint();

    //constructor
    public Target(int initX, int initY)
    {
        x = initX;
        y = initY;
        size = 132;
        radiusA = 132;
        radiusB = 88;
        radiusC = 44;
        velX = INITIAL_VELOCITY;
        velY = INITIAL_VELOCITY;
        red.setColor(Color.RED);
        white.setColor(Color.WHITE);
    }

    /** accessor methods */
    public double getXVel() { return velX; }
    public double getYVel() { return velY; }
    public float getSize() { return size; }

    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    public void drawMe(Canvas canvas){
        canvas.drawCircle(x,y,radiusA,red);
        canvas.drawCircle(x,y,radiusB,white);
        canvas.drawCircle(x,y,radiusC,red);
    }
    public void drawMeHit(Canvas canvas){
        canvas.drawCircle(x,y,radiusA,white);
        canvas.drawCircle(x,y,radiusB,red);
        canvas.drawCircle(x,y,radiusC,white);
    }
}
