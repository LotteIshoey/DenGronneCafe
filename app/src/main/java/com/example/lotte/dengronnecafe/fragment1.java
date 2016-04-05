package com.example.lotte.dengronnecafe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Lotte on 04-04-2016.
 */
public class fragment1 extends Fragment implements AdapterView.OnItemClickListener{

    ListView list;
    String[] desserts = {"Pancake", "Icecream","Cake", "Banasplit"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        list = (ListView) getActivity().findViewById(R.id.dessert_list);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,desserts);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Interface listener = (Interface) getActivity();
        listener.getPosition(position);

    }
}
