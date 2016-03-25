package com.example.lotte.dengronnecafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class ProfileMember extends AppCompatActivity {


    private static final int CAMERA_PIC_REQUEST = 1337;
    EditText profile_firstName, profile_lastName, profile_mail;
    ImageView profile_picture;
    Bitmap NewFace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_member);
        //No rotation possible
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        profile_firstName = (EditText) findViewById(R.id.edit_firstname);
        profile_lastName= (EditText) findViewById(R.id.edit_lastname);
        profile_mail = (EditText) findViewById(R.id.edit_mail);
        profile_picture = (ImageView) findViewById(R.id.MyFace);

        retrieveprofile(); //maybe in a super.onResume(); See slideshow


        //need to make a function to check if their already is information. if it exist then show the picture and text. showed in the bottom of a slide from lecture 6

    }

    public void click_takephoto (View v)
    {
        // opens camera when clicking on take_photo
        Intent MyCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(MyCameraIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            //we get the data if there is a picture
            NewFace = (Bitmap) data.getExtras().get("data");
            //we find the Imageview to put them
            ImageView image = (ImageView) findViewById(R.id.MyFace);
            image.setImageBitmap(NewFace);
        }
    }

    public void saveImage(Context context, Bitmap b, String name){
        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void click_saveprofile(View view){
        //create or modicfy a shared preference
        // MODE_PRIVATE: Only accessible in this application
        SharedPreferences ProfileSharedPreferences = getSharedPreferences("profilesetting", Activity.MODE_PRIVATE);
        // create an editor
        SharedPreferences.Editor editor = ProfileSharedPreferences.edit();
        //save entered value into preference
        editor.putString("firstname", profile_firstName.getText().toString());
        editor.putString("lastname", profile_lastName.getText().toString());
        editor.putString("mail", profile_mail.getText().toString());

        if (profile_picture != null) {
            saveImage(this,NewFace,"userphoto.jpg");
        }

        // save the prefernce
        editor.apply();
        Toast.makeText(this, "Din profil er gemt!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void retrieveprofile(){
        SharedPreferences ProfileSharedPreferences = getSharedPreferences("profilesetting", Activity.MODE_PRIVATE);
        String getting_firstname = ProfileSharedPreferences.getString("firstname", "");
        String getting_lastname = ProfileSharedPreferences.getString("lastname", "");
        String getting_mail = ProfileSharedPreferences.getString("mail", "");

        //place in layout
        profile_firstName.setText(getting_firstname);
        profile_lastName.setText(getting_lastname);
        profile_mail.setText(getting_mail);


        String path=this.getFilesDir().getAbsolutePath()+"/userphoto.jpg";
        File file = new File(path);
        if(file.exists()){
            profile_picture.setImageDrawable(Drawable.createFromPath(path));
        }
    }

    public void delete_profile(View v) {
        // Get the data from the shared preference and delete it.
        SharedPreferences preferences = getSharedPreferences("profilesetting", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        String path=this.getFilesDir().getAbsolutePath()+"/userphoto.jpg";
        File file = new File(path);
        if(file.exists()){
            deleteFile("userphoto.jpg");
        }

        Toast.makeText(this, "Din profil er slettet!", Toast.LENGTH_LONG).show();
        finish();
    }
}
