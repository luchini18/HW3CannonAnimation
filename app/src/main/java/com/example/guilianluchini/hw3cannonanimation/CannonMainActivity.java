package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * CannonMainActivity
 * 
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 * 
 * @author Andrew Nuxoll
 * @version September 2012
 *
 * edited by Guilian Luchini
 * April 2017
 * 
 */

/**
 External Citation
 Date:     2 April 2017
 Problem:  getting the cannon to rotate on seekbar change
 Resource: http://stackoverflow.com/questions/30365289/how-to-
 rotate-the-image-360-degree-by-using-seekbar-in-android
 Solution: I used example code from this post
 */

public class CannonMainActivity extends AppCompatActivity implements View.OnClickListener {
    //instance variables
    private SeekBar angleSB;
    private Button fireButton;
    private ImageView cannonImage;
    private int seek;
    CannonAnimator cannonAnim = new CannonAnimator();

	/**
	 * creates an AnimationCanvas containing a TestAnimator.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_main);

        //find views
        angleSB =(SeekBar) findViewById(R.id.angleSeekBar);
        fireButton = (Button) findViewById(R.id.fireButton);
        cannonImage = (ImageView) findViewById(R.id.cannonIV);

        //set max for seekbar
        angleSB.setMax(90);

        // Create an animation canvas and place it in the main layout
        final AnimationCanvas myCanvas = new AnimationCanvas(this, cannonAnim);
        LinearLayout mainLayout = (LinearLayout) this
                .findViewById(R.id.topLevelLayout);
        mainLayout.addView(myCanvas);



        //seek bar listener
        angleSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek = progress;
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.cannon);
                Matrix mat = new Matrix();
                mat.postRotate(-progress);
                Bitmap bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(), bMap.getHeight(), mat, true);
                cannonImage.setImageBitmap(bMapRotate);
            }
        });

        //button listener
        fireButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(android.view.View v){
                int viewId = v.getId();
                if(viewId == R.id.fireButton){
                    //fire cannon - start animation
                    int angle = angleSB.getProgress();
                }
            }
        });
	}

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.fireButton){
            //fire cannon - start animation
            int angle = angleSB.getProgress();
            cannonAnim.resetCount();
        }
    }

	/**
	 * This is the default behavior (empty menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cannon_main, menu);
		return true;
	}
}
