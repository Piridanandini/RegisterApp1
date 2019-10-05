package com.example.welcome.registerapp.requirement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.requirements;


import java.util.ArrayList;
import java.util.List;



public class installationListViewReq extends ArrayAdapter<requirements> {
    private Activity context;
    private List<requirements> installationListReq;

    public installationListViewReq(Activity context, List<requirements> installationList) {
        super(context, R.layout.installation_list_items_req, installationList);
        this.context = context;
        this.installationListReq = installationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.installation_list_items_req, null, true);
        TextView No_of_device=  listViewItem.findViewById(R.id.textView2);
        TextView site_name = listViewItem.findViewById(R.id.textView3);
        TextView device_name = listViewItem.findViewById(R.id.textView9);
        TextView region = listViewItem.findViewById(R.id.textView10);
        requirements install = installationListReq.get(position);
        No_of_device.setText(install.getNoofDevice());
        site_name.setText(install.getDeviceName());
        device_name.setText(install.getRegion());
        region.setText(install.getSiteName());
        Toast.makeText(context, install.getSiteName(), Toast.LENGTH_SHORT).show();
        return listViewItem;
    }

}
