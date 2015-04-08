package com.raoul.theridge;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class LocationActivity extends FragmentActivity {
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map)).getMap();
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(38.950745, -94.819260), 15));
//        Marker startPerc = map.addMarker(new MarkerOptions()
//                .position(new LatLng(38.950745, -94.819260))
//                .title("")
//                .snippet("Inizio del percorso")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
    }



}
