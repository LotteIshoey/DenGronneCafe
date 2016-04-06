package com.example.lotte.dengronnecafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Desserts extends AppCompatActivity implements Interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);
    }


    //this gets called when the user clicks an item in the list
    @Override
    public void getPosition(int p) {
        Fragment2 descriptionToText = (Fragment2) getFragmentManager().findFragmentById(R.id.fragment2);
        descriptionToText.setDescription(p);
        //FragmentManager manager = getFragmentManager();
        //Fragment2 finaldescription = manager.findFragmentById(R.id.fragment2);
    }



}
