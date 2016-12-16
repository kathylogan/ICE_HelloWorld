package com.cgifederal.ice_helloworld;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.parse.Parse;

/**
 * Created by cmanalan on 12/8/2016.
 */

public class ParseApplication extends MultiDexApplication {
    @Override

    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("geofence-ice")
                .clientKey("ekcjg9376mcnri")
                .server("http://geofence-ice.herokuapp.com/parse")
                .build());
    }
}
