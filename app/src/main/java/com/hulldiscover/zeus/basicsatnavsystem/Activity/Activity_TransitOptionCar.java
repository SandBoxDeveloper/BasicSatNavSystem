package com.hulldiscover.zeus.basicsatnavsystem.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.hulldiscover.zeus.basicsatnavsystem.R;

/**
 * Created by Zeus on 17/06/16.
 */
public class Activity_TransitOptionCar extends AppCompatActivity implements Animation.AnimationListener {

    // Views
    ImageView transitCar;

    // Animation
    Animation animFadein;
    Animation animZoomin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit_option_car);

        // load the animation
        animZoomin = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);

        // set animation listener
        animZoomin.setAnimationListener(this);

        // Get reference of imageView in the layout
        transitCar = (ImageView) findViewById(R.id.car_option_image);

        // start the animation
        transitCar.startAnimation(animZoomin);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for fade in animation
        if (animation == animFadein) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }


}
