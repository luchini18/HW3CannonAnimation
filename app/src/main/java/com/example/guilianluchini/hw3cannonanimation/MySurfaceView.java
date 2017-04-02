package com.example.guilianluchini.hw3cannonanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by guilianluchini on 3/31/17.
 */
/**
 External Citation
 Date:     31 March 2017
 Problem:  creating a surface view class from the lecture example
 Resource: Moodle/Nuxoll
 Solution: I used the example code from this post.
 */
public class MySurfaceView extends SurfaceView implements View.OnTouchListener {
    private void myInitializationStuff() {
        setWillNotDraw(false);

        //listen for the user to touch me
        this.setOnTouchListener(this);
    }

    public MySurfaceView(Context context) {
        super(context);
        myInitializationStuff();
    }

    public MySurfaceView(Context context, AttributeSet set) {
        super(context, set);
        myInitializationStuff();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        myInitializationStuff();
    }

    public void draw(Canvas canvas) {
        //sky
        Paint skyBlue = new Paint();
        int skyColor = Color.rgb(135,206,250);
        skyBlue.setColor(skyColor);
        Rect sky = new Rect(0,0,2000,2000);
        canvas.drawRect(sky,skyBlue);
        //water
        Paint waterBlue = new Paint();
        int waterColor = Color.rgb(0, 35, 155);
        waterBlue.setColor(waterColor);
        Rect grass = new Rect(0,750,2000,2000);
        canvas.drawRect(grass,waterBlue);

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
