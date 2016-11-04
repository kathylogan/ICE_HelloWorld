package com.cgifederal.ice_helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 8/12/2016.
 */
public class SecondaryPage extends Activity {
    ParseObject testObject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_page);

        Intent intent = getIntent();

        testObject = new ParseObject("TestObject");
    }

    /**
     * Click handler for "Push and Check" button
     * @param view
     */
    public void pushAndCheck(View view) {
        testObject.put("foo", "bar");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast myToast = Toast.makeText(getApplicationContext(), "posted to server", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    Toast myToast = Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });
    }

    /**
     * Click handler for Smiley Face image
     * @param view
     */
    public void smileyHandler(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "WE MADE IT 8/12/2016 AYUSH ROHATGI & THE ICE MOFOS WOOHOO", Toast.LENGTH_LONG);
        myToast.show();
    }
}
