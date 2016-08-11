package com.cgifederal.ice_helloworld;

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

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback{

    Button pushButton, readButton;
    ParseObject testObject, PointOfInterest;
    private GoogleMap mMap;
    List<PointOfInterest> parseObjects = new ArrayList<PointOfInterest>();

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
    query.getInBackground("70yRczwa6C", new GetCallback<ParseObject>(){
        public void done(ParseObject object, ParseException e){
            if (e == null){
                PointOfInterest = object;
            }
            else{
                //
            }
        }
            });



        testObject = new ParseObject("TestObject");
        //PointOfInterest = new ParseObject("PointOfInterest");

        pushButton = (Button) findViewById(R.id.pushButton);
        readButton = (Button) findViewById(R.id.readButton);

        pushButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //float val1 = testObject.get()
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.pushButton:
                testObject.put("foo", "bar");
                testObject.saveInBackground();
                Toast myToast = Toast.makeText(getApplicationContext(), "posted to server", Toast.LENGTH_SHORT);
                myToast.show();
                break;
            case R.id.readButton:
                /*String retStr = "hello";
                Boolean test = testObject.has("PointOfInterest");
                if(test){
                    retStr = "true";
                }
                else{
                    retStr = "false";
                }
                //ParseObject newObject = testObject.getParseObject("PointOfInterest");
                com.cgifederal.ice_helloworld.PointOfInterest poop = parseObjects.get(0);*/
                String location = PointOfInterest.getString("location");
                String[] latlng = location.split(",");
                float lat = Float.parseFloat(latlng[0]);
                float lng = Float.parseFloat(latlng[1]);
                LatLng test = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(test).title(PointOfInterest.getString("name")));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(test));
                Toast myToast2 = Toast.makeText(getApplicationContext(), location, Toast.LENGTH_LONG);
                myToast2.show();
                break;
        }
    }


}
