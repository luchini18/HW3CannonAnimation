package com.example.guilianluchini.hw3cannonanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
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
 * ///////PART A////////////////
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
 * ///////PART B////////////////
 * 5pts - Targets create animated "explosion" by inverting their colors when a cannonball hits them
 *
 * 8pts - Targets move around on screen until they are hit by a cannonball''
 *
 * 8pts - Included sound at cannon fire and target hit
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
/**
 External Citation
 Date:     7 April 2017
 Problem:  adding sounds to actions
 Resource: Moodle/Nuxoll
 Solution: I used example code from the Doodads demo
 */

public class CannonMainActivity extends AppCompatActivity implements View.OnClickListener{
    //instance variables
    private SeekBar angleSB;
    private Button fireButton,resetTargetsButton;
    private ImageView cannonImage;
    private int seek;
    private TextView hitTV, degreesTV;
    RealAnimator cannonAnim;
    // for playing sounds
    private SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private int fireSoundId,fuseSoundId;
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
        resetTargetsButton = (Button) findViewById(R.id.resetTargetsButton);
        cannonImage = (ImageView) findViewById(R.id.cannonIV);
        hitTV = (TextView) findViewById(R.id.hitTV);
        degreesTV = (TextView) findViewById(R.id.degreesTV);

        //set max for seekbar
        angleSB.setMax(90);

        // Create an animation canvas and place it in the main layout
        cannonAnim = new RealAnimator(this);
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
            resetTargetsButton.setOnClickListener(this);

        this.fireSoundId = soundPool.load(this,R.raw.cannonfire, 1);
        this.fuseSoundId = soundPool.load(this,R.raw.litfuse, 1);
	}

    //onClick, fires the cannon, decides if the cannon hit a target
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        //fire cannon - start animation
        if(viewId == R.id.fireButton){
            //send message to animator class
            int angle = angleSB.getProgress();
            cannonAnim.setDegrees(angle);
            cannonAnim.fireCannon();

            //start fuse burning and let it play for 1 second
            soundPool.play(this.fuseSoundId, 1, 1, 1, 0, 1.0f);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //do nothin
            }


            soundPool.play(this.fireSoundId, 1, 1, 1, 0, 1.0f);
            //wait to see if the cannonball hits a target
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                //do nothing
            }
            //after 1.7 seconds, check to see if a target has been hit
            if (cannonAnim.getHitCount() >= 1){
                hitTV.setText("YOU HIT THE TARGET!");
            }
            else{
                hitTV.setText("YOU MISSED");
            }

        }
        else if(viewId == R.id.resetTargetsButton){
            cannonAnim.resetTargets();
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
