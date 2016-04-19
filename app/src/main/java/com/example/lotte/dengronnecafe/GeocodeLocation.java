package com.example.lotte.dengronnecafe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodeLocation extends AppCompatActivity {

    TextView locationTxt;
    double currentLatitude;
    double currentLongitude;
    Location lastLocation = null;
    MyAsyncTask myAsyncTask;
    String currentStreet;
    String currentPost;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocode_location);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public class MyAsyncTask extends AsyncTask<Location, Void, String> {

        @Override
        protected String doInBackground(Location... params) {
            Location location = getLastLocation();
            reverseGeocode(location);
            return address;
        }

        @Override
        protected void onPreExecute() {
            locationTxt = (TextView) findViewById(R.id.geofind);
        }

        @Override
        protected void onPostExecute(String StreetResult) {
            locationTxt.setText("You are here: " + StreetResult);
        }

        private Location getLastLocation() {
            if (ActivityCompat.checkSelfPermission(GeocodeLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GeocodeLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.e("Location", "Missing location permission");
            }
            else{
                LocationManager locationManagaer = (LocationManager)
                        GeocodeLocation.this.getSystemService(GeocodeLocation.this.LOCATION_SERVICE);
                lastLocation = locationManagaer.getLastKnownLocation(locationManagaer.GPS_PROVIDER);
                Log.e("location", "yes it works!");
            }
            return lastLocation;
        }

        private void reverseGeocode(Location location) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            List<Address> addresses = null;
            Geocoder gc = new Geocoder(GeocodeLocation.this, Locale.getDefault());
            try {
                addresses = gc.getFromLocation(currentLatitude, currentLongitude, 1);
                currentStreet = addresses.get(0).getAddressLine(0);
                currentPost = addresses.get(0).getPostalCode();
                address = currentStreet + ", " + currentPost;

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
