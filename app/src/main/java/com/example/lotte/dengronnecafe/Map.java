package com.example.lotte.dengronnecafe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Map extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    GoogleApiClient mGoogleApiClient;
    TextView locationTxt;
    double cafeLatitude;
    double cafeLongitude;
    double currentLatitude;
    double currentLongitude;
    String currentStreet;
    String currentPost;
    LocationRequest mLocationRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationTxt = (TextView) findViewById(R.id.locationtext);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        forwardGeocode();
        // Zoom in on cafe
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(cafeLatitude, cafeLongitude), 13);
        googleMap.moveCamera(update);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        addMarker(cafeLatitude, cafeLongitude, "The Green Cafe");

    }


    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //Location listener. Update location every second
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); // update every second
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


       Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
       reverseGeocode(location);
        addMarker(currentLatitude, currentLongitude, "Your position");

        locationTxt.setText("You are here: " + currentStreet + ", " + currentPost);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void reverseGeocode(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        List<Address> addresses = null;
        Geocoder gc = new Geocoder(Map.this, Locale.getDefault());
        try {
            addresses = gc.getFromLocation(currentLatitude, currentLongitude, 1);
            currentStreet = addresses.get(0).getAddressLine(0);
            currentPost = addresses.get(0).getPostalCode();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void forwardGeocode() {
        Geocoder fwdGeocoder = new Geocoder(this, Locale.getDefault());
        String streetAddress = "Teglgårds Pl. 1, 9000 Aalborg";
        List<Address> locations = null;
        try {
            locations = fwdGeocoder.getFromLocationName(streetAddress, 1);
            cafeLongitude = locations.get(0).getLongitude();
            cafeLatitude = locations.get(0).getLatitude();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void addMarker(double latitude, double lontitude, String title) {

        GoogleMap mMap;
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        //TODO: getMap should be replaced by getMapAsync
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, lontitude))
                .title(title));
    }
}




