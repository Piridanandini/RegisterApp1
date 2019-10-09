package com.example.welcome.registerapp.service;
import android.content.Intent;
import android.os.Bundle;

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
import com.example.welcome.registerapp.database.service;
import com.example.welcome.registerapp.installation.ListViewAdapter;
import com.example.welcome.registerapp.installation.installationListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ThirdSerFragment extends Fragment {
    private ListView installationListService;
    private TextView installationId,service_date,vehicle_no,asset_code,device_name,device_imei_no, sim_name, sim_imei_no, sim_no, location, service_time, service_engineer_name, site_incharge_name, authorised_person;
    ListViewAdapter adapter;
    DatabaseReference installation_db;
    ArrayList<service> arrayList = new ArrayList<>();






    public ThirdSerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        installation_db = FirebaseDatabase.getInstance().getReference("services");

        View view = inflater.inflate(R.layout.fragment_third_ser, container, false);
        installationListService = (ListView) view.findViewById(R.id.listViewSer);
        installationListService.setDivider(null);


        installation_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                arrayList.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    service install = postSnapshot.getValue(service.class);

                    arrayList.add(install);
                }
                //creating adapter
                installationListViewService Adapter = new installationListViewService(getActivity(), arrayList);
                //attaching adapter to the listview
                installationListService.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        installationListService.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                service artist = arrayList.get(i);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.customservice, null);
                service_date = view1.findViewById(R.id.text);
                vehicle_no = view1.findViewById(R.id.text1);
                asset_code=view1.findViewById(R.id.text12);
                device_name = view1.findViewById(R.id.text2);
                device_imei_no = view1.findViewById(R.id.text3);
                sim_name = view1.findViewById(R.id.text4);
                sim_imei_no = view1.findViewById(R.id.text5);
                sim_no = view1.findViewById(R.id.text6);
                location = view1.findViewById(R.id.text7);
                service_time = view1.findViewById(R.id.text8);
                service_engineer_name = view1.findViewById(R.id.text9);
                site_incharge_name = view1.findViewById(R.id.text10);
                authorised_person = view1.findViewById(R.id.text11);

                service_date.setText(artist.getService_date());
                vehicle_no.setText(artist.getVehicle_no());

                asset_code.setText(artist.getAsset_code());
                device_name.setText(artist.getDevice_name());
                device_imei_no.setText(artist.getDevice_imei_no());
                sim_name.setText(artist.getSim_name());
                sim_imei_no.setText(artist.getSim_imei_no());
                sim_no.setText(artist.getSim_no());
                location.setText(artist.getLocation());
                service_time.setText(artist.getService_time());
                service_engineer_name.setText(artist.getService_engineer_name());
                site_incharge_name.setText(artist.getSite_incharge_name());
                authorised_person.setText(artist.getAuthorised_person());
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

        installationListService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                EditText installationId,service_date,vehicle_no,asset_code,device_name,device_imei_no, sim_name, sim_imei_no, sim_no, location, service_time, service_engineer_name, site_incharge_name, authorised_person;


                service artist = arrayList.get(i);
                showUpdateDeleteDialog(artist,artist.getInstallationId(), artist.getService_date());
                return true;
            }
        });
        return view;
    }

    private void showUpdateDeleteDialog(service artist, final String artistId, String artistName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog_service, null);
        service_date = dialogView.findViewById(R.id.text);
        vehicle_no = dialogView.findViewById(R.id.text1);
        asset_code=dialogView.findViewById(R.id.text12);
        device_name = dialogView.findViewById(R.id.text2);
        device_imei_no = dialogView.findViewById(R.id.text3);
        sim_name = dialogView.findViewById(R.id.text4);
        sim_imei_no =dialogView.findViewById(R.id.text5);
        sim_no = dialogView.findViewById(R.id.text6);
        location = dialogView.findViewById(R.id.text7);
        service_time = dialogView.findViewById(R.id.text8);
        service_engineer_name = dialogView.findViewById(R.id.text9);
        site_incharge_name = dialogView.findViewById(R.id.text10);
        authorised_person = dialogView.findViewById(R.id.text11);

        service_date.setText(artist.getService_date());
        vehicle_no.setText(artist.getVehicle_no());
        asset_code.setText(artist.getAsset_code());
        device_name.setText(artist.getDevice_name());
        device_imei_no.setText(artist.getDevice_imei_no());
        sim_name.setText(artist.getSim_name());
        sim_imei_no.setText(artist.getSim_imei_no());
        sim_no.setText(artist.getSim_no());
        location.setText(artist.getLocation());
        service_time.setText(artist.getService_time());
        service_engineer_name.setText(artist.getService_engineer_name());
        site_incharge_name.setText(artist.getSite_incharge_name());
        authorised_person.setText(artist.getAuthorised_person());

        dialogBuilder.setView(dialogView);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);
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
                deleteArtist(artistId);
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

