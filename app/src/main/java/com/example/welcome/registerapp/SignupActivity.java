package com.example.welcome.registerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       Button button = (Button)findViewById(R.id.add_device);
        // set corner radius value
        // Implement onClickListener event on CardView
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(SignupActivity.this,AdddeviceActivity.class);
                startActivity(intent);

            }
        });




        Button button1 = (Button)findViewById(R.id.install);
        // set corner radius value
        // Implement onClickListener event on CardView
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View.OnClickListener context = this;

                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
