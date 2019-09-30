package com.example.welcome.registerapp.installation;

/**
 * Created by welcome on 9/16/2019.
 */

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.welcome.registerapp.R;
import com.example.welcome.registerapp.VehicleNames;
import java.util.ArrayList;


public class SecondFragment extends Fragment implements SearchView.OnQueryTextListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] vehicleNameList;
    ArrayList<VehicleNames> arraylist = new ArrayList<VehicleNames>();


    public SecondFragment() {
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        list = (ListView) view.findViewById(R.id.listview);
        vehicleNameList = new String[]{"101", "102", "301", "501", "601", "801", "999", "788", "865", "133", "789", "878"};
        for (int i = 0; i < vehicleNameList.length; i++) {
            VehicleNames vehicleNames = new VehicleNames(vehicleNameList[i]);
            // Binds all strings into an array
            arraylist.add(vehicleNames);
            adapter = new ListViewAdapter(getActivity(), arraylist);
            list.setAdapter(adapter);
            editsearch = (SearchView) view.findViewById(R.id.search);
            editsearch.setOnQueryTextListener(this);
        }
        return view;


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}


