package com.example.lotte.dengronnecafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity {

    public void click_menu (View v){
        Intent menu_start = new Intent(getApplicationContext(),Menu.class);
        startActivity(menu_start);
        // maybe implement: finish() tells that it is destroyed when another activity is running - can not show anything if back is pressed.
    }

    public void click_profilemember (View v){
        Intent profile_start = new Intent(getApplicationContext(),ProfileMember.class);
        startActivity(profile_start);
        // maybe implement: finish() tells that it is destroyed when another activity is running - can not show anything if back is pressed.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }
}
