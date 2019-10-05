package com.example.welcome.registerapp.service;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.service;

import java.util.ArrayList;
import java.util.List;



public class installationListViewService extends ArrayAdapter<service> {
    private Activity context;
    private List<service> installationListService;

    public installationListViewService(Activity context, List<service> installationList) {
        super(context, R.layout.installation_list_items_service, installationList);
        this.context = context;
        this.installationListService = installationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.installation_list_items_service, null, true);
        TextView ServiceDate=  listViewItem.findViewById(R.id.textView2);
        TextView VehicleNo = listViewItem.findViewById(R.id.textView3);
        TextView Location = listViewItem.findViewById(R.id.textView9);
        TextView DeviceNo = listViewItem.findViewById(R.id.textView10);
        service install = installationListService.get(position);
        ServiceDate.setText(install.getService_date());
        VehicleNo.setText(install.getVehicle_no());
        Location.setText(install.getLocation());
        DeviceNo.setText(install.getAuthorised_person());
        return listViewItem;
    }

}