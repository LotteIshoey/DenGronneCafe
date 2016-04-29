package com.example.lotte.dengronnecafe;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Lotte on 22-04-2016.
 */
public class MenuToDatabase extends AppCompatActivity {

    //THIS IS NOT USED!

    private static int hasPopulatedMenu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DatabaseHelper myhelper = new DatabaseHelper(this);
        SQLiteDatabase db = myhelper.getWritableDatabase();

        if (hasPopulatedMenu == 0) {
            insertMenuDB (db,"Salatbar", 49.00, R.drawable.fav_icon);
            insertMenuDB (db,"Sandwich", 55.00, R.drawable.arrow_icon);
            insertMenuDB (db,"Minipizza", 20.00, R.drawable.arrow_icon);
            insertMenuDB (db, "Organic drink", 25.00, R.drawable.new_icon);
            insertMenuDB (db, "Smoothies", 29.00, R.drawable.fav_icon);
            insertMenuDB (db, "Coffee/Tea", 20.00, R.drawable.arrow_icon);
            insertMenuDB (db,"Special Coffee", 30.00, R.drawable.arrow_icon);
            hasPopulatedMenu++;
        }
        populateMenuListDB(); //prøv at sætte den op i if-statementet?
    }

    public void populateMenuListDB() {
        //maybe first nessesary when using the database for listView
        DatabaseHelper myhelper = new DatabaseHelper(this);
        SQLiteDatabase db = myhelper.getWritableDatabase();

        //Get all information from database table
        Cursor menuCursor = db.rawQuery("Select * FROM " + myhelper.TABLE_MENU, null);
        // Store the desired colum values
        String[] PresentedData = new String[] {myhelper.NAME, myhelper.MENU_PRICE, myhelper.MENU_PICTURE};
        // Store the views to be used for the ListView
        int[] PresentedViews = new int[] {R.id.salatName, R.id.salatPrice, R.id.menuPic};
        // Create the adapter to convert the array to views
        SimpleCursorAdapter menuCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_menu, menuCursor, PresentedData, PresentedViews,0);
        // Attach the adapter to a ListView
        ListView menuListview = (ListView) findViewById(R.id.lvMenu);
        menuListview.setAdapter(menuCursorAdapter);

    }


    public void insertMenuDB (SQLiteDatabase db,  String name, double price, int imageRessource) {

        DatabaseHelper myhelper = new DatabaseHelper(this);
        db = myhelper.getWritableDatabase();

        ContentValues menuValues = new ContentValues();
        menuValues.put(myhelper.NAME, name);
        menuValues.put(myhelper.MENU_PRICE, price);
        menuValues.put(myhelper.MENU_PICTURE, imageRessource);
        db.insert(myhelper.TABLE_MENU, null, menuValues);

    }
}
