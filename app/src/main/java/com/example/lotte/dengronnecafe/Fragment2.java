package com.example.lotte.dengronnecafe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lotte on 04-04-2016.
 */
public class Fragment2 extends Fragment {
    TextView text;

    String[] dessert_description = {"HelloA", "HelloB","HelloC", "HelloD"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        text = (TextView) view.findViewById(R.id.fragment_text);
        return view;
    }
    }
