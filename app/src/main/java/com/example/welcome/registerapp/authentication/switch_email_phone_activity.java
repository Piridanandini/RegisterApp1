package com.example.welcome.registerapp.authentication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.welcome.registerapp.HomeActivity;
import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.add_installations;

public class switch_email_phone_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_email_phone_activity);
    }

    public void email_button(View view) {
        Intent intent = new Intent(this, email_login_activity.class);
        startActivity(intent);
    }

    public void phone_button(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }
}
