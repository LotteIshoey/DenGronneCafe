package com.example.lotte.dengronnecafe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 1;
    public final String TABLE_MENU = "Drinks";
    public final String TABLE_EVENTS = "Events";

    public static final String PERSON_ID = "_id";
    public final String NAME = "name";
    public final String MENU_PICTURE = "image";
    public final String MENU_PRICE = "price";

    public static final String EVENT_ID = "_id";
    public final String EVENT_PERSON = "person";
    public final String EVENT_DRINK = "drink";
    public final String EVENT_DATE = "date"; //TODO: need to be changed later on
    public final String EVENT_TIME = "time";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table_drink = "CREATE TABLE " + TABLE_MENU + " (" + PERSON_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + MENU_PICTURE + " INTEGER, " + MENU_PRICE + " INTEGER);";

        String table_event = "CREATE TABLE " + TABLE_EVENTS + " (" + EVENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_PERSON + " TEXT, " + EVENT_DRINK + " TEXT, " + EVENT_DATE + " TEXT, " + EVENT_TIME + " TEXT);";

        db.execSQL(table_drink);
        db.execSQL(table_event);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int DATABASE_VERSION) {
        if(DATABASE_VERSION > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
            onCreate(db);

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
            onCreate(db);

            // TODO: old version is NOT defined
        }


    }
}
