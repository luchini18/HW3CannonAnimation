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
    public static final int MAX_VELOCITY = 5;
    //instance variables
    private int x;
    private int y;
    private int size;
    private int velX;
    private int velY;
    private Paint myPaint;

    //constructor
    public CannonBall(int initX, int initY, int initSize)
    {
        x = initX;
        y = initY;
        size = initSize;
        myPaint.setColor(Color.BLACK);

        //The velocity is set to a random value within range
        Random randGen = new Random();
        velX = randGen.nextInt(MAX_VELOCITY*2) - MAX_VELOCITY;
        velY = randGen.nextInt(MAX_VELOCITY*2) - MAX_VELOCITY;
    }

    /** accessor methods */
    public int getX() { return x; }
    public int getY() { return y; }

    /** mutator methods */
    public void setPos(int newX, int newY) { x = newX; y = newY; }
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
        //Update position
        x += velX;
        y += velY;

        //Bounce as necessary
        if (x < 0)
        {
            x = -x;
            velX = -velX;
        }
        else if (x > width)
        {
            x = width - (x - width);
            velX = -velX;
        }

        if (y < 0)
        {
            y = -y;
            velY = -velY;
        }
        else if (y > height)
        {
            y = height - (y - height);
            velY = -velY;
        }
    }//move

    /**
     * paint
     *
     * paints the ball on a given canvas
     *
     * @param canvas   the canvas
     */
    public void paint(Canvas canvas)
    {
        canvas.drawCircle(x,y,size,myPaint);
    }


}

