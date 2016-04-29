package com.example.lotte.dengronnecafe;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Event extends AppCompatActivity {

    EditText change_eventDate;
    EditText change_eventTime;
    Spinner spinner_eventFood;
    EditText change_eventPerson;

    SQLiteDatabase db;

    //for finding a person on the contact list
    static final int PICK_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //No rotation possible
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //Reference to the views
        change_eventDate = (EditText) findViewById(R.id.edit_date);
        change_eventTime = (EditText) findViewById(R.id.edit_time);
        spinner_eventFood = (Spinner) findViewById(R.id.spinner_menu);
        change_eventPerson = (EditText) findViewById(R.id.findcontact_view);

        //Generate Spinner items from database
        ArrayList<String> allFood = GetMenu();
        Spinner menuSpinner = (Spinner) findViewById(R.id.spinner_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,allFood);
        menuSpinner.setAdapter(adapter);
    }

    public void eventSave (View view){
        String date = change_eventDate.getText().toString();
        String time = change_eventTime.getText().toString();
        String food = spinner_eventFood.toString();
        String person = change_eventPerson.getText().toString();

        DatabaseHelper myhelper = new DatabaseHelper(this);
        db = myhelper.getWritableDatabase();


        if (!date.equals("") && !time.equals("") && !food.equals("") && !person.equals("")) {
            ContentValues event = new ContentValues();
            event.put(myhelper.EVENT_DATE, date);
            event.put(myhelper.EVENT_TIME, time);
            event.put(myhelper.EVENT_CHOSEN_FOOD, food);
            event.put(myhelper.EVENT_PERSON, person);
            db.insert(myhelper.TABLE_EVENTS, null, event);
            Log.d("Event:", "Event is saved into database table");
            Toast.makeText(this, "Your event is the " + date + " at " + time + " together with " + person, Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(this, "You need to fill it all out", Toast.LENGTH_SHORT).show();


        }

    }

    //Generate spinner items from database
    public ArrayList<String> GetMenu(){
        ArrayList<String> menu = new ArrayList<>();

        DatabaseHelper myhelper = new DatabaseHelper(this);
        db = myhelper.getReadableDatabase();

        Cursor menuCursor = db.rawQuery("Select * FROM " + myhelper.TABLE_MENU, null);

        if(menuCursor.getCount()>0){
            menuCursor.moveToFirst();
            while(menuCursor.moveToNext()){
                String foodName = menuCursor.getString(menuCursor.getColumnIndex(myhelper.NAME));
                menu.add(foodName);
            }
        }
        return menu;
    }

    public void invite_friend(View view)
    {
        Intent contactintent = new Intent (Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactintent, PICK_CONTACT);
    }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) {

            Uri contactData = data.getData();
            ContentResolver resolver = getContentResolver();
            Cursor cur = resolver.query(contactData, null, null, null, null);
            cur.moveToFirst();

            int nameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String contactname = cur.getString(nameColumn);

            TextView contact = (TextView) findViewById(R.id.findcontact_view);
            contact.setText(contactname);
        }
    }
}



