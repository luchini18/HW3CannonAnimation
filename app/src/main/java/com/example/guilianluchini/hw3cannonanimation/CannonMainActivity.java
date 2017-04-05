package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

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
 * Edits include:
 * activity_cannon_main.xml to display a cannon, targets,
 * animation canvas and controls for the cannon
 *
 * CannonBall class stores velocity, color and size of a
 * cannonball and is usedin RealAnimator Class
 *
 * RealAnimator class implements Animator interface and fires
 * the cannon consistently with the laws of gravity, although
 * this cannon is pretty powerful
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
    private TextView hitTV, degreesTV;
    RealAnimator cannonAnim = new RealAnimator();

	/**
	 * creates an AnimationCanvas containing a RealAnimator and initializes views
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_main);

        //find views
        angleSB =(SeekBar) findViewById(R.id.angleSeekBar);
        fireButton = (Button) findViewById(R.id.fireButton);
        cannonImage = (ImageView) findViewById(R.id.cannonIV);
        hitTV = (TextView) findViewById(R.id.hitTV);
        degreesTV = (TextView) findViewById(R.id.degreesTV);

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
                degreesTV.setText("" + seek + " Degrees");

            }
        });

            //button listener
            fireButton.setOnClickListener(this);

	}
    //onClick, fires the cannon, decides if the cannon hit a target
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        //fire cannon - start animation
        if(viewId == R.id.fireButton){
            //send angle to animator class and reset count
            int angle = angleSB.getProgress();
            cannonAnim.setDegrees(angle);
            cannonAnim.resetCount();

            //set bounds for if the target was hit or not
            if (angle <= 7 || (angle >= 23 && angle <= 34)){
                /**
                 External Citation
                 Date:     5 April 2017
                 Problem:  delaying the setting of the text view
                 Resource: http://stackoverflow.com/questions/14186846/delay-actions-in-android
                 Solution: I used example code from this post
                 */
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        hitTV.setText("YOU HIT THE TARGET!");
                    }

                }, 1400);//delay 1.4 seconds to see if the ball hits the target

            }
            else{
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        hitTV.setText("YOU MISSED");
                    }

                }, 1400);//delay 1.4 seconds to see if the ball hits the target

            }
        }
    }

	/**
	 * This is the default behavior (empty menu)
     * I might use this in HW 3 b
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cannon_main, menu);
		return true;
	}
}
