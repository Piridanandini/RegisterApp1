package com.example.welcome.registerapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.welcome.registerapp.installation.TabActivity;
import com.example.welcome.registerapp.planner.PlannerActivity;
import com.example.welcome.registerapp.requirement.Tab3Activity;
import com.example.welcome.registerapp.service.Tab2Activity;
import com.example.welcome.registerapp.support.SupportActivity;

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


        CardView cardView4 = (CardView) findViewById(R.id.card6);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this,LogoutActivity.class);
                startActivity(intent);

            }
        });


        CardView cardView5 = (CardView) findViewById(R.id.card4);
        // set corner radius value
        // Implement onClickListener event on CardView
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(HomeActivity.this, Tab3Activity.class);
                startActivity(intent);

            }
        });











    }
}










