package com.example.welcome.registerapp.database;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.welcome.registerapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class add_installations extends AppCompatActivity {
    List<installation> installationList;
    DatabaseReference installation_db;
    Button btn;
    EditText vehicle_type, vehicle_no,  device_name,  device_imei_no,  sim_name,  sim_imei_no,  sim_no,  location,  service_time,  service_engineer_name,  site_incharge_name,  authorised_person;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        btn = findViewById(R.id.signup);
        installation_db = FirebaseDatabase.getInstance().getReference("installation");
        vehicle_type = (EditText) findViewById(R.id.vehicle_type);
        vehicle_no = (EditText) findViewById(R.id.vehicle_no);
        device_name = (EditText) findViewById(R.id.device_name);
        device_imei_no = (EditText) findViewById(R.id.device_imei_no);
        sim_name = (EditText) findViewById(R.id.sim_name);
        sim_imei_no = (EditText) findViewById(R.id.sim_imei_no);
        sim_no = (EditText) findViewById(R.id.sim_no);
        service_time = (EditText) findViewById(R.id.service_time);
        service_engineer_name = (EditText) findViewById(R.id.service_engg_name);
        site_incharge_name = (EditText) findViewById(R.id.site_incharge_name);
        authorised_person = (EditText) findViewById(R.id.authorized_person);

        installationList =  new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstallations();
            }
        });




    }
     public void addInstallations() {

         String Vehicle_type = vehicle_type.getText().toString().trim();
         String Vehicle_no = vehicle_no.getText().toString().trim();
         String Device_name = device_name.getText().toString().trim();
         String Device_imei_no = device_imei_no.getText().toString().trim();
         String Sim_name = sim_name.getText().toString().trim();
         String Sim_imei_no = sim_imei_no.getText().toString().trim();
         String Sim_no = sim_no.getText().toString().trim();
         String Location = "location6543";
         String Service_time = service_time.getText().toString().trim();
         String Service_engineer_name = service_engineer_name.getText().toString().trim();
         String Site_incharge_name = site_incharge_name.getText().toString().trim();
         String Authorised_person = authorised_person.getText().toString().trim();


         if (!TextUtils.isEmpty(Vehicle_type)) {

             String installationid = installation_db.push().getKey();

             installation installation = new installation(installationid,
                     Vehicle_type, Vehicle_no, Device_name,
                     Device_imei_no, Sim_name, Sim_imei_no,
                     Sim_no, Location, Service_time,
                     Service_engineer_name, Site_incharge_name, Authorised_person);

             installation_db.child(installationid).setValue(installation);
             vehicle_no.setText("");
             device_name.setText("");
             device_imei_no.setText("");
             sim_name.setText("");
             sim_imei_no.setText("");
             sim_no.setText("");
             location.setText("");
             service_time.setText("");
             service_engineer_name.setText("");
             site_incharge_name.setText("");
             authorised_person.setText("");

             Toast.makeText(this, "Installation details uploaded successfully", Toast.LENGTH_SHORT).show();

         }
         else {
             Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
         }












    }



}
