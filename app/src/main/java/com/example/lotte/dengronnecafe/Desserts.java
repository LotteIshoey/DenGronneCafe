package com.example.lotte.dengronnecafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Desserts extends AppCompatActivity implements Interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);
    }


    @Override
    public void getPosition(int p) {
        //handle det fragment 2. in p we have the position


    }




}
