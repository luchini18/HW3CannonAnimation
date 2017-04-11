package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by guilianluchini on 4/7/17.
 */
public class Ground {
    private int width,height,l,t,r,b;
    private Paint grass,dirt;

    public Ground(int initWidth, int initHeight){
        width = initWidth;
        height = initHeight;
        grass = new Paint();
        dirt = new Paint();
        grass.setColor(Color.rgb(60,255,60));
        dirt.setColor(Color.rgb(100,50,0));
        l = 0;
        t = height*29/32;
        r = width;
        b = height;
    }
    public void drawMe(Canvas canvas){
        //draw dirt first
        canvas.drawRect(l,t,r,b,dirt);
        //draw grass on top
        t = height*14/16;
        b = height*29/32;
        canvas.drawRect(l,t,r,b,grass);
    }

}

