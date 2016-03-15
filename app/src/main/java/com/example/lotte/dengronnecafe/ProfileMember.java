package com.example.lotte.dengronnecafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileMember extends AppCompatActivity {


    private static final int CAMERA_PIC_REQUEST = 1337;
    String firstName = "";
    String lastName = "";
    String email = "";
    int pictureID;
    private SharedPreferences prefs;
    EditText editText;




    public void click_takephoto (View v)
    {
    Intent MyCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(MyCameraIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode ==
                Activity.RESULT_OK) {
            //we get the data
            Bitmap NewFace = (Bitmap) data.getExtras().get("data");
            //we find the Imageview to put them
            ImageView image = (ImageView) findViewById(R.id.MyFace);
            image.setImageBitmap(NewFace);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_member);

        editText = (EditText) findViewById(R.id.edit_firstname);
        editText = (EditText) findViewById(R.id.edit_lastname);
        editText = (EditText) findViewById(R.id.edit_mail);

    }

    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
