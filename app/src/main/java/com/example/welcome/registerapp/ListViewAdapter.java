package com.example.welcome.registerapp;

/**
 * Created by welcome on 9/25/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    private Context mContext;
    private LayoutInflater inflater;
    private List<VehicleNames> vehicleNamesList = null;
    private ArrayList<VehicleNames> arraylist;

    public ListViewAdapter(Context context, List<VehicleNames> vehicleNamesList) {
        mContext = context;
        this.vehicleNamesList = vehicleNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<VehicleNames>();
        this.arraylist.addAll(vehicleNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return vehicleNamesList.size();
    }

    @Override
    public VehicleNames getItem(int position) {
        return vehicleNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(vehicleNamesList.get(position).getVehicleName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        vehicleNamesList.clear();
        if (charText.length() == 0) {
            vehicleNamesList.addAll(arraylist);
        } else {
            for (VehicleNames wp : arraylist) {
                if (wp.getVehicleName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    vehicleNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}