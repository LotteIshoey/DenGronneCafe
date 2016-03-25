package com.example.lotte.dengronnecafe;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
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
        //No rotation possible
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //THIS IS THE SIMPLE ADAPTER
       /* String[] myMenuArray = {"Salatbar", "Sandwich eller wraps","Grillede Sandwich", "Økologisk Saft", "Kaffe"};

        String[] myMenusubline = {"a", "b", "c", "d", "e"};

        //Simple List Adapter
        ArrayAdapter<String> MenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myMenuArray);

        //assosiate adapter with textview
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(MenuAdapter);*/

        DatabaseHelper myhelper = new DatabaseHelper(this);
        SQLiteDatabase db = myhelper.getWritableDatabase();

        int updatenumber = 0;
        if(updatenumber == 0){
            ContentValues valuesToInsert= new ContentValues();
            valuesToInsert.put(myhelper.NAME,"Salatbar");
            valuesToInsert.put(myhelper.MENU_PICTURE,R.drawable.arrow_icon);
            valuesToInsert.put(myhelper.MENU_PRICE,49);
            long id = db.insert(myhelper.TABLE_MENU, null, valuesToInsert);
        }
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




