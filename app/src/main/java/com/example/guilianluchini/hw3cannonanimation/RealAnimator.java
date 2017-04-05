package com.example.guilianluchini.hw3cannonanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by guilianluchini on 4/1/17.
 */

public class RealAnimator implements Animator {

    //start the count at zero
    private int count = 0;
    private int degrees = 0;

    //cannonball variables
    private CannonBall cannonBall = new CannonBall(500,1000,45);
    private double xVel = cannonBall.getXVel();
    private double yVel = cannonBall.getYVel();

    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 30;
    }
    @Override
    public int backgroundColor() {
        //set background as a nice light blue
        return Color.rgb(180, 200, 255);
    }

    @Override
    public boolean doPause() {
        //never pause
        return false;
    }

    @Override
    public boolean doQuit() {
        //never quit
        return false;
    }

    public void resetCount(){
        count = 0;
    }
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
        canvas.drawCircle(x,y,cannonBall.getSize(),cannonBall.getMyPaint());
    }

    @Override
    public void onTouch(MotionEvent event) {
        //don't do anything when the view is touched
    }
}