package com.example.welcome.registerapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.welcome.registerapp.authentication.phone_login_activity;

public class WelcomeActivity extends AppCompatActivity {

        private ViewFlipper simpleViewFlipper;
        int[] images = {R.drawable.carflip2, R.drawable.img,R.drawable.carflip3};     // array of images

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
            getSupportActionBar().hide(); // hide the title bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
            setContentView(R.layout.activity_signup);

            // get The references of ViewFlipper
            simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

            // loop for creating ImageView's
            for (int i = 0; i < images.length; i++) {
                // create the object of ImageView
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(images[i]); // set image in ImageView
                simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
            }
            // Declare in and out animations and load them using AnimationUtils class
            Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
            // set the animation type's to ViewFlipper
            simpleViewFlipper.setInAnimation(in);
            simpleViewFlipper.setOutAnimation(out);

            // set interval time for flipping between views
            simpleViewFlipper.setFlipInterval(2000);
            // set auto start for flipping between views
            simpleViewFlipper.setAutoStart(true);


            Button button = (Button)findViewById(R.id.Loginbutton);
            // set corner radius value
            // Implement onClickListener event on CardView
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final View.OnClickListener context = this;
                    Intent intent = new Intent(WelcomeActivity.this, phone_login_activity.class);
                    startActivity(intent);

                }
            });

        }
    }