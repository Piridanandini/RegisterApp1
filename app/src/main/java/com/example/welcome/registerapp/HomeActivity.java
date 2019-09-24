package com.example.welcome.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        CardView cardView = (CardView) findViewById(R.id.card1);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this, TabActivity.class);
                startActivity(intent);

            }
        });


        CardView cardView1 = (CardView) findViewById(R.id.card2);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this, Tab2Activity.class);
                startActivity(intent);

            }
        });


        CardView cardView2 = (CardView) findViewById(R.id.card3);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this, PlannerActivity.class);
                startActivity(intent);

            }
        });


        CardView cardView3 = (CardView) findViewById(R.id.card5);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this, SupportActivity.class);
                startActivity(intent);

            }
        });










    }
}










