package com.example.welcome.registerapp.installation;
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
import android.widget.Toast;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.VehicleNames;
import com.example.welcome.registerapp.database.installation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SecondFragment extends Fragment {
    private ListView installationList;
    ListViewAdapter adapter;
    DatabaseReference installation_db;
    ArrayList<installation> arrayList = new ArrayList<installation>();
    public static final String ARTIST_NAME = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String ARTIST_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";





    public SecondFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        installation_db = FirebaseDatabase.getInstance().getReference("installation");

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        installationList = (ListView) view.findViewById(R.id.listView);
        installationList.setDivider(null);

        installation_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                arrayList.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    installation install = postSnapshot.getValue(installation.class);

                    arrayList.add(install);
                }
                //creating adapter
                installationListView Adapter = new installationListView(getActivity(), arrayList);
                //attaching adapter to the listview
                installationList.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        installationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                installation artist = arrayList.get(i);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.custom, null);
                alertDialog.setView(dialogLayout);
                alertDialog.setPositiveButton("OK", null);
                alertDialog.setView(dialogLayout);
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

        installationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                installation install = arrayList.get(i);
                showUpdateDeleteDialog(install.getInstallationId(), install.getVehicle_no());
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
                deleteArtist(artistId);
                b.dismiss();
            }
        });
    }

    private boolean deleteArtist(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("installation").child(id);
        //removing artist
        dR.removeValue();
        Toast.makeText(getActivity(), "vehicle Deleted", Toast.LENGTH_LONG).show();
        return true;
    }




}


