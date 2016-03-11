package com.example.lotte.dengronnecafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        populateMenuList();

       /* String[] myMenuArray = {"Salatbar", "Sandwich eller wraps","Grillede Sandwich", "Økologisk Saft", "Kaffe"};

        String[] myMenusubline = {"a", "b", "c", "d", "e"};

        //Simple List Adapter
        ArrayAdapter<String> MenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myMenuArray);

        //assosiate adapter with textview
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(MenuAdapter);*/

    }
    private void populateMenuList(){
        // Construct the data source
        ArrayList<Menulist> arrayOfMenus = Menulist.getMenus();
        //Create the adapter to convert the array to views
        MenuAdapter adapter = new MenuAdapter(this, arrayOfMenus);
        //attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvMenu);
        listView.setAdapter(adapter);
    }







}




