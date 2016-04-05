package com.example.lotte.dengronnecafe;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Event extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //No rotation possible
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    static final int PICK_CONTACT = 1;
    public void invite_friend(View view)
    {
        Intent contactintent = new Intent (Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
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
