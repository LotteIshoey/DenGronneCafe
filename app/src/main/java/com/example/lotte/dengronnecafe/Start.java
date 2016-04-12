package com.example.lotte.dengronnecafe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity {

    public void click_menu (View v){
        Intent menu_start = new Intent(getApplicationContext(),Menu.class);
        startActivity(menu_start);
      }

    public void click_profilemember (View v){
        Intent profile_start = new Intent(getApplicationContext(),ProfileMember.class);
        startActivity(profile_start);
     }

    public void click_about (View v){
        Intent about_start = new Intent(getApplicationContext(),About.class);
        startActivity(about_start);
     }

    public void click_contact (View v){
        Intent contact_start = new Intent(getApplicationContext(),Kontakt.class);
        startActivity(contact_start);
    }

    public void click_dessert (View v){
        Intent contact_dessert = new Intent(getApplicationContext(),Desserts.class);
        startActivity(contact_dessert);
    }

    public void click_find (View v){
        Intent contact_find = new Intent(getApplicationContext(),Map.class);
        startActivity(contact_find);
    }

    public void click_music (View v){
        Intent music_activity = new Intent(getApplicationContext(),Topsongs.class);
        startActivity(music_activity);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //No rotation possible
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}
