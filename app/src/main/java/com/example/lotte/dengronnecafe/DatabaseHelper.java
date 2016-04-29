package com.example.lotte.dengronnecafe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 1;

    //The menu table
    public final String TABLE_MENU = "Drinks";

    public static final String DRINK_ID = "_id";
    public final String NAME = "name";
    public final String MENU_PICTURE = "image";
    public final String MENU_PRICE = "price";

    //The event table
    public final String TABLE_EVENTS = "Events";
    public static final String EVENT_ID = "_id";
    public final String EVENT_PERSON = "person";
    public final String EVENT_DRINK = "drink";
    public final String EVENT_DATE = "date";
    public final String EVENT_TIME = "time";
    public final String EVENT_CHOSEN_FOOD = "Food";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the two tables
        String table_drink = "CREATE TABLE " + TABLE_MENU + " (" + DRINK_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + MENU_PICTURE + " TEXT, " + MENU_PRICE + " REAL);";

        String table_event = "CREATE TABLE " + TABLE_EVENTS + " (" + EVENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_PERSON + " TEXT, " + EVENT_DRINK + " TEXT, " + EVENT_DATE + " TEXT, " + EVENT_TIME + " TEXT, " + EVENT_CHOSEN_FOOD + " TEXT);";

        db.execSQL(table_drink);
        db.execSQL(table_event);
        //TODO: show the image in the listview
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Salad', 'R.drawable.fav_icon', '49.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Sandwich', 'R.drawable.arrow_icon', '55.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Mini Pizza', 'R.drawable.arrow_icon', '20.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Orgaic Drink', 'R.drawable.new_icon', '25.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Smoothie', 'R.drawable.fav_icon', '29.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Coffee/tea', 'R.drawable.arrow_icon', '20.00');");
        db.execSQL("insert into " + TABLE_MENU + " values(null, 'Special Coffee', 'R.drawable.arrow_icon', '30.00');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int DATABASE_VERSION) {
        if(DATABASE_VERSION > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
            onCreate(db);
        }
    }
}
