package com.example.lotte.dengronnecafe;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Menu extends AppCompatActivity {

    private static int hasPopulatedMenu = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        populateMenuListDB();



        //THIS IS THE SIMPLE ADAPTER
       /* String[] myMenuArray = {"Salatbar", "Sandwich eller wraps","Grillede Sandwich", "Økologisk Saft", "Kaffe"};

        String[] myMenusubline = {"a", "b", "c", "d", "e"};

        //Simple List Adapter
        ArrayAdapter<String> MenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myMenuArray);

        //assosiate adapter with textview
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(MenuAdapter);*/


        //THIS IS FOR THE COSTUM ADAPTER
        /*int updatenumber = 0;
        if(updatenumber == 0){
            ContentValues valuesToInsert= new ContentValues();
            valuesToInsert.put(myhelper.NAME,"Salatbar");
            valuesToInsert.put(myhelper.MENU_PICTURE,R.drawable.arrow_icon);
            valuesToInsert.put(myhelper.MENU_PRICE,49);
            long id = db.insert(myhelper.TABLE_MENU, null, valuesToInsert);
        }*/
    }


    //THIS IS FOR THE COSTUM ADAPTER
/*
    private void populateMenuList(){
        // Construct the data source
        ArrayList<Menulist> arrayOfMenus = Menulist.getMenus();
        //Create the adapter to convert the array to views
        MenuAdapter adapter = new MenuAdapter(this, arrayOfMenus);
        //attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvMenu);
        listView.setAdapter(adapter);
   }*/

    public void populateMenuListDB() {

        DatabaseHelper myhelper = new DatabaseHelper(this);
        SQLiteDatabase db = myhelper.getWritableDatabase();

        if (hasPopulatedMenu < 1) {
            //insertdata();
        }
            //Get all information from database table
            Cursor menuCursor = db.rawQuery("Select * FROM " + myhelper.TABLE_MENU, null);
            // Store the desired colum values
            String[] PresentedData = new String[]{myhelper.NAME, myhelper.MENU_PRICE, myhelper.MENU_PICTURE};
            // Store the views to be used for the ListView
            int[] PresentedViews = new int[]{R.id.salatName, R.id.salatPrice, R.id.menuPic};
            // Create the adapter to convert the array to views
            SimpleCursorAdapter menuCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_menu, menuCursor, PresentedData, PresentedViews, 0);
            // Attach the adapter to a ListView
            ListView menuListview = (ListView) findViewById(R.id.lvMenu);
            menuListview.setAdapter(menuCursorAdapter);
        }

/*    public void insertMenuDB (SQLiteDatabase db,  String name, double price, int imageRessource) {
            DatabaseHelper myhelper = new DatabaseHelper(this);
            db = myhelper.getWritableDatabase();

            ContentValues menuValues = new ContentValues();
            menuValues.put(myhelper.NAME, name);
            menuValues.put(myhelper.MENU_PRICE, price);
            menuValues.put(myhelper.MENU_PICTURE, imageRessource);
            db.insert(myhelper.TABLE_MENU, null, menuValues);
    }

    public void insertdata(){
        DatabaseHelper myhelper = new DatabaseHelper(this);
        SQLiteDatabase db = myhelper.getWritableDatabase();

    insertMenuDB(db, "Salatbar", 49.00, R.drawable.fav_icon);
    insertMenuDB(db, "Sandwich", 55.00, R.drawable.arrow_icon);
    insertMenuDB(db, "Minipizza", 20.00, R.drawable.arrow_icon);
    insertMenuDB(db, "Organic drink", 25.00, R.drawable.new_icon);
    insertMenuDB(db, "Smoothies", 29.00, R.drawable.fav_icon);
    insertMenuDB(db, "Coffee/Tea", 20.00, R.drawable.arrow_icon);
    insertMenuDB(db, "Special Coffee", 30.00, R.drawable.arrow_icon);
        hasPopulatedMenu++;
    }*/
}




