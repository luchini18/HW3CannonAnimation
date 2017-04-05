package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by guilianluchini on 4/1/17.
 *
 * RealAnimator
 * (as opposed to TestAnimator)
 * Fires a cannonball at high velocity at user
 * specified angle to see if they can hit a target
 */

public class RealAnimator implements Animator {

    //start the count at zero
    private int count = 0;
    private int degrees = 0;

    //cannonball variables
    private CannonBall cannonBall = new CannonBall(500,1000);
    private double xVel = cannonBall.getXVel();
    private double yVel = cannonBall.getYVel();
    private float size = cannonBall.getSize();
    private Paint paint = cannonBall.getMyPaint();
    //0.3 seconds between frames
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        //set background as a nice light blue
        return Color.rgb(180, 200, 255);
    }

    //never pause
    @Override
    public boolean doPause() {
        return false;
    }

    //never quit
    @Override
    public boolean doQuit() {
        return false;
    }
    //used to reset animation when cannon is fired
    public void resetCount(){
        count = 0;
    }

    //used to tell the progress of the seekbar
    public void setDegrees(int d){degrees = d;}

    @Override
    public void tick(Canvas canvas) {
        count++;
        //convert degrees to radians
        double launchAngle = degrees*3.14/180;
        //x,y coordinates to account for gravity including starting position
        double xPos = (count * xVel* Math.cos(launchAngle))+ 300;
        double yPos = ((count * yVel* Math.sin(launchAngle)) - ((1/2)*9.8*count*count)) - 400;

        //convert x,y to int
        int x = (int)xPos;
        //correct y axis ~600 pixels
        int y = 600 - (int) yPos;

        //add cannon ball in right spot
        canvas.drawCircle(x,y,size,paint);
    }

    //don't do anything when the view is touched
    @Override
    public void onTouch(MotionEvent event) {
        return;
    }
}