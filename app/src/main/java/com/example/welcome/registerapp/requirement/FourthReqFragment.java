package com.example.welcome.registerapp.requirement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.VehicleNames;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.requirements;
import com.example.welcome.registerapp.database.service;
import com.example.welcome.registerapp.installation.ListViewAdapter;
import com.example.welcome.registerapp.installation.installationListView;
import com.example.welcome.registerapp.service.installationListViewService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FourthReqFragment extends Fragment {
    private ListView installationListReq;
    installationListViewReq Adapter1;
    private TextView no_of_device,type_of_device,region,device_name,site_name,email,mobile_no,address,pincode;
    DatabaseReference installation_db1;
    List<requirements> arrayList = new ArrayList<requirements>();


    public FourthReqFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        installation_db1 = FirebaseDatabase.getInstance().getReference("services");
        View view = inflater.inflate(R.layout.fragment_fourth_req, container, false);
        installationListReq = view.findViewById(R.id.listViewreq);
        installationListReq.setDivider(null);


        installation_db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                arrayList.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    requirements install = postSnapshot.getValue(requirements.class);

                    arrayList.add(install);

                }
                //creating adapter
                Adapter1 = new installationListViewReq(getActivity(), arrayList);
                //attaching adapter to the listview
                installationListReq.setAdapter(Adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "db error", Toast.LENGTH_SHORT).show();
            }
        });

        installationListReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                requirements artist = arrayList.get(i);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.customreq, null);
               no_of_device = view1.findViewById(R.id.text);
               type_of_device = view1.findViewById(R.id.text1);
                region=view1.findViewById(R.id.text12);
                device_name = view1.findViewById(R.id.text2);
                site_name = view1.findViewById(R.id.text3);
                email = view1.findViewById(R.id.text4);
                mobile_no = view1.findViewById(R.id.text5);
                address= view1.findViewById(R.id.text6);
                pincode = view1.findViewById(R.id.text7);
                no_of_device.setText(artist.getNoofDevice());
                type_of_device.setText(artist.getTypeofDevice());
               region.setText(artist.getRegion());
                device_name.setText(artist.getDeviceName());
                site_name.setText(artist.getSiteName());
               email.setText(artist.getEmail());
               mobile_no.setText(artist.getMobileNo());
                address.setText(artist.getAddress());
               pincode.setText(artist.getPincode());
               alertDialog.setView(view1);
                alertDialog.setPositiveButton("OK", null);
                alertDialog.setView(view1);
                // create and show the alert dialog
                AlertDialog dialog = alertDialog.create();
                // Initialize a new window manager layout parameters
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();


                // Copy the alert dialog window attributes to new layout parameter instance
                layoutParams.copyFrom(dialog.getWindow().getAttributes());

                // Set the width and height for the layout parameters
                // This will bet the width and height of alert dialog
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

                // Apply the newly created layout parameters to the alert dialog window
                dialog.getWindow().setAttributes(layoutParams);
                alertDialog.show();


            }
        });

        installationListReq.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                requirements install = arrayList.get(i);
                //showUpdateDeleteDialog(install.getInstallationId(), install.getNoofDevice());
                return true;
            }
        });
        return view;
    }

    private void showUpdateDeleteDialog(final String artistId, String artistName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);
        dialogBuilder.setTitle(artistName.toUpperCase());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    updateArtist(artistId, name, genre);
                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                b.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  deleteArtist(artistId);
                b.dismiss();
            }
        });
    }

    private boolean deleteArtist(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //removing artist
        dR.removeValue();

        Toast.makeText(getActivity(), "vehicle Deleted", Toast.LENGTH_LONG).show();
        return true;
    }




}

