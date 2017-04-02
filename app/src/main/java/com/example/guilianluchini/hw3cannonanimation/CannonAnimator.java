package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by guilianluchini on 4/1/17.
 */

public class CannonAnimator implements Animator {
    @Override
    public int interval() {
        return 0;
    }

    @Override
    public int backgroundColor() {
        return 0;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
