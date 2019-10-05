package com.example.welcome.registerapp.requirement;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.requirements;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirstReqFragment extends Fragment {
    List<requirements> installationList;
    DatabaseReference installation_db;
    EditText installationId,  noofDevice,  typeofDevice,  region,  deviceName,  siteName,  email,  mobileNo,  address,  pincode;
    Button btn;

    public FirstReqFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        installation_db = FirebaseDatabase.getInstance().getReference("requirements");


        installationList =  new ArrayList<>();
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_req, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        noofDevice = view.findViewById(R.id.NoofDevice);
       typeofDevice= view.findViewById(R.id.TypeofDevice);
        region = view.findViewById(R.id.Region);
        deviceName= view.findViewById(R.id.DeviceName);
        siteName = view.findViewById(R.id.SiteName);
        email = view.findViewById(R.id.Email);
       mobileNo = view.findViewById(R.id.MobileNo);
        address = view.findViewById(R.id.Address);
        pincode = view.findViewById(R.id.Pincode);

        btn = view.findViewById(R.id.signup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstallations();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }


    public void addInstallations() {

        String  NoofDevice  = noofDevice.getText().toString().trim();
        String TypeofDevice = typeofDevice.getText().toString().trim();
        String Region = region.getText().toString().trim();
        String  DeviceName = deviceName.getText().toString().trim();
        String  SiteName= siteName.getText().toString().trim();
        String Email = email.getText().toString().trim();

        String MobileNo =mobileNo.getText().toString().trim();
        String Address= address.getText().toString().trim();

        String Pincode= pincode.getText().toString().trim();


        if (!TextUtils.isEmpty( NoofDevice )) {

            String installationid = installation_db.push().getKey();

            requirements req = new requirements(installationid,
                    NoofDevice,
                    TypeofDevice,
                    Region,DeviceName,SiteName,Email,MobileNo, Address, Pincode);


            installation_db.child(installationid).setValue(req);
            noofDevice.setText("");
            typeofDevice.setText("");
           region.setText("");
           deviceName.setText("");
            siteName.setText("");
            email.setText("");
            mobileNo.setText("");
           address.setText("");
            pincode.setText("");

            Toast.makeText(getActivity(), "Installation details uploaded successfully", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }





    }



}


