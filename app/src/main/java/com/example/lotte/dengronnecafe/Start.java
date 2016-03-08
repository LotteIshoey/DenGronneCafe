package com.example.lotte.dengronnecafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity {

    public void click_menu (View v){
        Intent menu_start = new Intent(getApplicationContext(),Menu.class);
        startActivity(menu_start);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }
}
