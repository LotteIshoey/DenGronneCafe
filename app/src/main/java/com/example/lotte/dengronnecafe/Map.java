package com.example.lotte.dengronnecafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Zoom
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(57.046429, 9.932824), 13);
        googleMap.moveCamera(update);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Position and Title
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(57.046429, 9.932824))
                .title("The Green Cafe"));

    }




}
