package com.kingtvarshin.oasis2016new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by lenovo on 15-09-2016.
 */
public class Splashscreen extends Activity {

    ImageView rotateImage1,rotateImage2;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rotateImage1 = (ImageView) findViewById(R.id.imageView2);
        Animation startRotateAnimation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotatorright);
        rotateImage1.startAnimation(startRotateAnimation1);

        rotateImage2 = (ImageView) findViewById(R.id.imageView);
        Animation startRotateAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotatorleft);
        rotateImage2.startAnimation(startRotateAnimation2);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */



            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}