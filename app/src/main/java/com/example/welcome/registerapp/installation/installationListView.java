package com.example.welcome.registerapp.installation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.installation;

import java.util.ArrayList;
import java.util.List;



public class installationListView extends ArrayAdapter<installation> {
    private Activity context;
    List<installation> installationList;

    public installationListView(Activity context, List<installation> installationList) {
        super(context, R.layout.installation_list_items, installationList);
        this.context = context;
        this.installationList = installationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.installation_list_items, null, true);
        TextView textViewName =  listViewItem.findViewById(R.id.textView2);
        installation install = installationList.get(position);
        textViewName.setText(install.getVehicle_no());
        return listViewItem;
    }

}