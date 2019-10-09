package com.example.welcome.registerapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.welcome.registerapp.HomeActivity;
import com.example.welcome.registerapp.R;

public class Admin_authentication extends AppCompatActivity {
    ImageButton button;
    EditText edituser,editpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_admin_authentication);


        edituser = findViewById(R.id.EditUser);
        editpass = findViewById(R.id.EditPass);
        button = findViewById(R.id.AdminBack);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(edituser.getText());
                String p = String.valueOf(editpass.getText());
                if(s.equals("tracalogic") && p.equals("tracalogic@123"))
                {
                    Toast.makeText(Admin_authentication.this, "you are now an admin, You can now update or delete from database", Toast.LENGTH_SHORT).show();
                    ((global_vars) getApplication()).setSomeVariable("foo");
                }

            }

        });

    }
}
