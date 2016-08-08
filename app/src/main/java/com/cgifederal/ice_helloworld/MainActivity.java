package com.cgifederal.ice_helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button pushButton, readButton;
    ParseObject testObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        pushButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.pushButton:
                testObject.put("foo", "bar");
                testObject.saveInBackground();
                Toast myToast = Toast.makeText(getApplicationContext(), "posted to server", Toast.LENGTH_LONG);
                myToast.show();
                break;
            case R.id.readButton:
                String retStr = testObject.getString("foo");
                Toast myToast2 = Toast.makeText(getApplicationContext(), retStr, Toast.LENGTH_LONG);
                myToast2.show();
                break;
        }
    }


}
