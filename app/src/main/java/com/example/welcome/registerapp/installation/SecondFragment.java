package com.example.welcome.registerapp.installation;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.VehicleNames;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.planner.PlannerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;


public class SecondFragment extends Fragment {
    ListView installationList;
    ListViewAdapter adapter;
    DatabaseReference installation_db;

    ArrayList<installation> arrayList = new ArrayList<installation>();
    public static final String Install_Name = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String Install_Id = "net.simplifiedcoding.firebasedatabaseexample.artistid";



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
                    //adding artist to the list
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
                {
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
            }
        });

        return view;


    }

}


