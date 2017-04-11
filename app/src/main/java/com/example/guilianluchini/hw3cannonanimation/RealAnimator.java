package com.example.guilianluchini.hw3cannonanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by guilianluchini on 4/1/17.
 *
 * RealAnimator
 * (as opposed to TestAnimator)
 * Fires a cannonball at high velocity at user
 * specified angle to see if they can hit a target
 */

public class RealAnimator implements Animator {
    //instance variables
    private CannonBall cannonBall;
    private Context context;
    private int cannonBallCount;
    private int target1Count;
    private int target2Count;
    private int degrees;
    private int hitCount;
    private SoundPool soundPool;
    private int hitSoundId;
    Bitmap unlitFuse,litFuse1,litFuse2,litFuse3;
    //booleans
    boolean right;
    boolean up;
    boolean fire;
    boolean fuse;
    //targets
    private Target target1;
    private Target target2;

    public RealAnimator(Context initContext){
        context = initContext;
        //start the count at zero
        cannonBallCount = 0;
        target1Count = 0;
        target2Count = 0;
        degrees = 0;
        hitCount = 0;
        //sound for hitting targets
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        hitSoundId = soundPool.load(context, R.raw.targethit, 1);
        //booleans
        right = true;
        up = true;
        fire = false;
        unlitFuse = BitmapFactory.decodeResource(context.getResources(), R.drawable.fuse);
        litFuse1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.litfuse1);
        litFuse2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.litfuse2);
        litFuse3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.litfuse3);
    }

    //0.3 seconds between frames
    public int interval() {
        return 30;
    }

    //set background as a nice light blue
    @Override
    public int backgroundColor() {
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
    public void fireCannon(){
        fire = true;
        cannonBallCount = 0;
        hitCount = 0;
    }

    //used to tell the progress of the seekbar
    public void setDegrees(int d){degrees = d;}

    //reset target positions
    public void resetTargets(){
        target1Count = 0;
        target2Count = 0;
    }

    //used to tell if a target has been hit
    public int getHitCount(){return hitCount;}

    /** hitOrMiss
     * decides if a cannon is hitting a target, if not, draw target as
     * normal, if it is, invert colors of target to notify user that
     * they hit the target
     *
     * @param target target in question
     * @param canvas canvas to draw on
     */
    public void hitOrMiss(Target target, Canvas canvas){
        boolean hit = false;
        //if a cannonball hasnt been fired, draw target normally
        if (!fire){
            target.drawMe(canvas);
        }
        else { //if a cannonball has been fired, see if it hits a target
            //decide if ball hits cannon - if ball pos intersects target pos
            if (cannonBall.overlaps(target)) {
                hit = true;
            }
            //if it hasnt been hit by a cannonball, draw the target
            if (!hit) {
                target.drawMe(canvas);
            } else {
                target.drawMeHit(canvas);
                hitCount++;
                //play sound on first hit
                if (hitCount == 1){
                    soundPool.play(this.hitSoundId, 1, 1, 1, 0, 1.0f);
                }
            }
        }
    }


    @Override
    public void tick(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        //draw the ground
        Ground ground = new Ground(width,height);
        ground.drawMe(canvas);

        //wait until user presses fire, then do some math and initialize cannon
        if (fire) {
            cannonBallCount++;
            //animate fuse
            Rect rectangle;
            if (cannonBallCount <= 5 ) {
                rectangle = new Rect(145,935,195,1035);
                canvas.drawBitmap(unlitFuse,null,rectangle,null);
            }
            else if (cannonBallCount <= 10 ) {
                rectangle = new Rect(145,940,195,1035);
                canvas.drawBitmap(litFuse1, null, rectangle, null);
            }
            else if (cannonBallCount <= 15) {
                rectangle = new Rect(145,945,195,1035);
                canvas.drawBitmap(litFuse2,  null, rectangle, null);
            }
            else if (cannonBallCount <= 20 ) {
                rectangle = new Rect(145,950,195,1035);
                canvas.drawBitmap(litFuse3,  null, rectangle, null);
            }


            //convert degrees to radians
            double launchAngle = degrees * Math.PI / 180;
            //x,y coordinates to account for gravity including starting position
            double xPos = (cannonBallCount * 25 * Math.cos(launchAngle)) + (width / 6);
            double yPos = ((cannonBallCount * 25 * Math.sin(launchAngle)) - ((1 / 2) * 9.8 * cannonBallCount * cannonBallCount)) + (height / 5);

            //convert x,y to int
            int x = (int) xPos;
            //correct y axis
            int y = height - (int) yPos;

            cannonBall = new CannonBall(x, y);

            //add cannon ball in right spot
            cannonBall.drawMe(canvas);
        }

        //moving targets
        //starting point
        int t1X = width*9/10;
        int t1Y = height/5;

        int t2X = width*4/5;
        int t2Y = height/5;

        //target 1 moving left or right
        if(right) {
            target1Count++;
            t1X = t1X + target1Count*12;
            target1 = new Target(t1X, t1Y);
            //draw target, seeing if cannonball has hit it or not
            hitOrMiss(target1,canvas);
            if (t1X >= (width - 132)){right = false;}
        }
        else{
            target1Count--;
            t1X = t1X + target1Count*12;
            target1 = new Target(t1X, t1Y);
            //draw target, seeing if cannonball has hit it or not
            hitOrMiss(target1,canvas);
            if (t1X <= (132)){right = true;}
        }

        //target 2 moving up or down
        if(up) {
            target2Count++;
            t2Y = t2Y + target2Count*12;
            t2Y = height - t2Y;
            target2 = new Target(t2X, t2Y);
            //draw target, seeing if cannonball has hit it or not
            hitOrMiss(target2,canvas);
            if (t2Y <= (132)){up = false;}
        }
        else{
            target2Count--;
            t2Y = t2Y + target2Count*12;
            t2Y = height - t2Y;
            target2 = new Target(t2X, t2Y);
            //draw target, seeing if cannonball has hit it or not
            hitOrMiss(target2,canvas);
            if (t2Y >=(height - 132 - (height*2/16))){up = true;}
        }
    }



    //don't do anything when the view is touched
    @Override
    public void onTouch(MotionEvent event) {
        return;
    }
}