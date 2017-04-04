package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * Created by guilianluchini on 4/1/17.
 */

public class CannonAnimator implements Animator {

    //start the count at zero
    private int count = 0;

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

    @Override
    public void tick(Canvas canvas) {
        count++;

        //x,y coordinates to account for gravity
        double xPos = (count*xVel+500);

        double yMath1 = yVel*count;
        double yMath2 = -9.8/2*count*count;
        double yPos =  yMath1+yMath2+1000;

        int x = (int)xPos;
        int y = (int)yPos;
        //draw cannon ball in right spot
        cannonBall.paint(x,y,canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {
        //don't do anything when the view is touched
    }
}
