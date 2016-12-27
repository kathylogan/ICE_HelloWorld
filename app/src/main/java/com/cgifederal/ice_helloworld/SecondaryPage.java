package com.cgifederal.ice_helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.ParseQuery;
import com.parse.GetCallback;

/**
 * Created by Ayush on 8/12/2016.
 */
public class SecondaryPage extends Activity {
    PointOfInterest testObject;

    private static final String NOTIFICATION_MSG = "NOTIFICATION MSG";
    // Create a Intent send by the notification
    public static Intent makeNotificationIntent(Context context, String msg, String pointOfInterestId) {
        Intent intent = new Intent(context, SecondaryPage.class);
        intent.putExtra(NOTIFICATION_MSG, msg);
        intent.putExtra(Intent.EXTRA_TEXT, pointOfInterestId);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_page);

        Intent intent = getIntent();
        String pointOfInterestId = intent.getStringExtra(Intent.EXTRA_TEXT);
        // set data based on Parse record

        ParseQuery<PointOfInterest> query = new ParseQuery("PointOfInterest");
         query.getInBackground(pointOfInterestId, new GetCallback<PointOfInterest>() {
             public void done(PointOfInterest object, ParseException e) {
                 if (e == null) {
                     updateObject(object);
                 } else {
                     //NULL;
                 }
             }
         });
    }

    protected void updateObject(PointOfInterest object) {
        setContentView(R.layout.secondary_page);
        String fName = "Brownfield";
        String pName = object.getString("name");
        String eBody = object.getString("community_description");

        TextView featureName = (TextView) findViewById(R.id.featureName);
        featureName.setText(fName);
        TextView pointOfInterestName = (TextView) findViewById(R.id.pointOfInterestName);
        pointOfInterestName.setText(pName);
        TextView eventBody = (TextView) findViewById(R.id.eventBody);
        eventBody.setText(eBody);
    }
}
