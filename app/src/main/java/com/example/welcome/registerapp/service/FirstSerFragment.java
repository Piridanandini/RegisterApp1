package com.example.welcome.registerapp.service;

/**
 * Created by welcome on 9/16/2019.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.service;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FirstSerFragment extends Fragment {

    List<service> installationList;
    DatabaseReference installation_db;
    EditText service_date, vehicle_no,asset_code, device_name,  device_imei_no,  sim_name,  sim_imei_no,  sim_no,  location,  service_time,  service_engineer_name,  site_incharge_name,  authorised_person;

    Button btn;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;

    public FirstSerFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_ser, container, false);

        installation_db = FirebaseDatabase.getInstance().getReference("services");


        installationList =  new ArrayList<>();

        imageView = (ImageView) view.findViewById(R.id.imageView);

        view.findViewById(R.id.btnChoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            999);
                }
                else {

                }
            }
        });
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        service_date = view.findViewById(R.id.vehicle_type);
        vehicle_no = view.findViewById(R.id.vehicle_no);
        asset_code=view.findViewById(R.id.asset_code);
        device_name = view.findViewById(R.id.device_name);
        device_imei_no = view.findViewById(R.id.device_imei_no);
        sim_name = view.findViewById(R.id.sim_name);
        location = view.findViewById(R.id.location);
        sim_imei_no = view.findViewById(R.id.sim_imei_no);
        sim_no = view.findViewById(R.id.sim_no);
        service_time = view.findViewById(R.id.service_time);
        service_engineer_name = view.findViewById(R.id.service_engg_name);
        site_incharge_name = view.findViewById(R.id.site_incharge_name);
        authorised_person = view.findViewById(R.id.authorized_person);

        btn = view.findViewById(R.id.signup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstallations();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

//            private void startGallery() {
//                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                cameraIntent.setType("image/*");
//                if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivityForResult(cameraIntent, 1000);
//                }
//            }
//
//
//            @Override
//            public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//                if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
//                    Uri uri = data.getData();
//
//                    try {
//                        InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
//
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//
//                        imageView.setImageBitmap(bitmap);
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                super.onActivityResult(requestCode, resultCode, data);
//            }

    public void addInstallations() {

        String Service_date = service_date.getText().toString().trim();
        String Vehicle_no = vehicle_no.getText().toString().trim();
        String Asset_code = asset_code.getText().toString().trim();
        String Device_name = device_name.getText().toString().trim();
        String Device_imei_no = device_imei_no.getText().toString().trim();
        String Sim_name = sim_name.getText().toString().trim();
        String Sim_imei_no = sim_imei_no.getText().toString().trim();
        String Sim_no = sim_no.getText().toString().trim();
        String Location = location.getText().toString().trim();
        String Service_time = service_time.getText().toString().trim();
        String Service_engineer_name = service_engineer_name.getText().toString().trim();
        String Site_incharge_name = site_incharge_name.getText().toString().trim();
        String Authorised_person = authorised_person.getText().toString().trim();







        if (!TextUtils.isEmpty(Vehicle_no)) {

            String installationid = installation_db.push().getKey();

            service installation = new service(installationid,
                   Service_date, Vehicle_no, Asset_code, Device_name,
                    Device_imei_no, Sim_name, Sim_imei_no,
                    Sim_no, Location, Service_time,
                    Service_engineer_name, Site_incharge_name, Authorised_person);

            installation_db.child(installationid).setValue(installation);
            service_date.setText("");
            vehicle_no.setText("");
            asset_code.setText("");
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

            Toast.makeText(getActivity(), "Installation details uploaded successfully", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }





    }



    }

