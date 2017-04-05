package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

/**
 * Created by guilianluchini on 3/28/17.
 */
/**
 External Citation
 Date:     1 April 2017
 Problem:  creating a ball that moves across the screen
 Resource: Moodle/Nuxoll
 Solution: I used the ball example in this class
 */

public class CannonBall {
    private final double INITIAL_VELOCITY = 30;
    //instance variables

    private float size;
    private double velX;
    private double velY;
    private Paint myPaint = new Paint();
    private double launchAngle;

    //constructor
    public CannonBall(double initX, double initY,double initLaunchAngle)
    {

        launchAngle = initLaunchAngle;
        size = 25;
        myPaint.setColor(Color.BLACK);

        velX = INITIAL_VELOCITY;
                //* Math.cos(launchAngle);
        velY = INITIAL_VELOCITY;
                //* Math.sin(launchAngle);
    }

    /** accessor methods */
    public double getXVel() { return velX; }
    public double getYVel() { return velY; }
    public float getSize() { return size; }
    public Paint getMyPaint() {return myPaint;}
    /** mutator methods */
    //public void setPos(int newX, int newY) { x = newX; y = newY; }
    public void setSize(int newSize) { size = newSize; }


    /**
     * move
     *
     * causes the ball's position to change based upon velocity.  The ball is
     * presumed to be moving in a rectangular area defined by the x/y axes and a
     * given width and height.  If the ball has reached a "wall" it bounces.
     *
     */
    public void move(int width, int height)
    {
//      Update position
//        x = velX;
//        y = velY - 9.81/2;

        //Bounce as necessary
//        if (x < 0)
//        {
//            x = -x;
//            velX = -velX;
//        }
//        else if (x > width)
//        {
//            x = width - (x - width);
//            velX = -velX;
//        }
//
//        if (y < 0)
//        {
//            y = -y;
//            velY = -velY;
//        }
//        else if (y > height)
//        {
//            y = height - (y - height);
//            velY = -velY;
//        }
    }

    /**
     * paint
     *
     * paints the ball on a given canvas
     *
     * @param canvas   the canvas
     */
    public void paint(float x, float y,Canvas canvas)
    {
        canvas.drawCircle(x,y,size,myPaint);
    }


}

