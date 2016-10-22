package com.cgifederal.ice_helloworld;

import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    Button pushButton, readButton;
    public ParseObject PointOfInterest;
    public GoogleMap mMap;
    //List<PointOfInterest> parseObjects = new ArrayList<PointOfInterest>();

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
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("geofence-ice")
                .clientKey("ekcjg9376mcnri")
                .server("http://geofence-ice.herokuapp.com/parse")
                .build());

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        ParseObject.registerSubclass(PointOfInterest.class);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PointOfInterest");

        // create a stub object, then replace it if we can connect to Parse
        PointOfInterest = ParseObject.create("PointOfInterest");
        PointOfInterest.put("location", "12,12");
        PointOfInterest.put("name", "Sample Point - error connecting to Parse");
        query.getInBackground("70yRczwa6C", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    PointOfInterest = object;
                } else {
                    // TODO: handle error
                }
            }
        });

        pushButton = (Button) findViewById(R.id.pushButton);
        readButton = (Button) findViewById(R.id.readButton);


        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add logic for click handler
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = PointOfInterest.getString("location");
                String[] latlng = location.split(",");
                float lat = Float.parseFloat(latlng[0]);
                float lng = Float.parseFloat(latlng[1]);
                LatLng test = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(test).title(PointOfInterest.getString("name")));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(test));
                Toast myToast2 = Toast.makeText(getApplicationContext(), PointOfInterest.getString("name"), Toast.LENGTH_LONG);
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

    public void openPage(View view) {
        Intent intent = new Intent(this, SecondaryPage.class);
        startActivity(intent);
    }
}
