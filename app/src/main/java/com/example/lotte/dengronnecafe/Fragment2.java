package com.example.lotte.dengronnecafe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    TextView description;
    String activeDescription;
    String[] array_description = {"With sugar, sirup, or jelly", "With strawberry, vanilla, or chocolate","Differs", "With Banana, chocolate icecream, vanilla icecream and weapcream"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);

        if(savedInstanceState != null){
            activeDescription = savedInstanceState.getString("text");
            description = (TextView) view.findViewById(R.id.fragment_text);
            description.setText(activeDescription);
        }
        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        description = (TextView) getActivity().findViewById(R.id.fragment_text);
    }

    //save the fragment state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", activeDescription);
    }


    public void setDescription (int p){
        this.activeDescription = array_description[p];
        description.setText(activeDescription);
    }

    }

