package com.cgifederal.ice_helloworld;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback{

    Button pushButton, readButton;
    ParseObject testObject;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        /*Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("geofence-ice")
                .clientKey("ekcjg9376mcnri")
                .server("http://geofence-ice.herokuapp.com/parse")
                .build());*/


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        testObject = new ParseObject("TestObject");

        pushButton = (Button) findViewById(R.id.pushButton);
        readButton = (Button) findViewById(R.id.readButton);

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testObject.put("foo", "bar");
                testObject.saveInBackground();
                Toast myToast = Toast.makeText(getApplicationContext(), "posted to server", Toast.LENGTH_LONG);
                myToast.show();
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retStr = testObject.getString("foo");
                Toast myToast2 = Toast.makeText(getApplicationContext(), retStr, Toast.LENGTH_LONG);
                myToast2.show();
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
