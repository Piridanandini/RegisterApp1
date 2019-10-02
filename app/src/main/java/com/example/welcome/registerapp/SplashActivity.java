package com.example.welcome.registerapp;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat( PixelFormat.RGBA_8888 );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_splash);

        final ImageView splash_image = (ImageView) findViewById(R.id.splash_image);

        final Animation animation_2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);


        animation_2.reset();

        splash_image.clearAnimation();
        splash_image.startAnimation(animation_2);




        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        thread.start();
    }}