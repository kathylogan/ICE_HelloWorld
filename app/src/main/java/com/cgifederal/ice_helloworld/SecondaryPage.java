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
    PointOfInterest replyObject;

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

        testObject = new PointOfInterest();
        replyObject = new PointOfInterest();
        testObject.put("Feature", "Test Feature Name");
        testObject.setFeature("The Feature Name");
        testObject.put("pointOfInterestName", "Point of Interest Name");
        testObject.put("eventBody", "Test Event Body");

        String objectId = testObject.getID();

        ParseQuery<PointOfInterest> query = new ParseQuery("PointOfInterest");
         query.getInBackground(objectId, new GetCallback<PointOfInterest>() {
             public void done(PointOfInterest object, ParseException e) {
                 if (e == null) {
                     replyObject.put("Feature", object.getFeature());
                     replyObject.setFeature(object.getFeature());
                     updateObject(replyObject);
                 } else {
                     //NULL;
                 }
             }
         });

        Intent intent = getIntent();
        String pointOfInterestId = intent.getStringExtra(Intent.EXTRA_TEXT);
        // set data based on Parse record

    }

    protected void updateObject(PointOfInterest object) {
        setContentView(R.layout.secondary_page);
        String fName = replyObject.getFeature();
        String pName = object.getString("pointOfInterestName");
        String eBody = object.getString("eventBody");

        TextView featureName = (TextView) findViewById(R.id.featureName);
        //featureName.setText("Brownfield");
        featureName.setText(fName);
        TextView pointOfInterestName = (TextView) findViewById(R.id.pointOfInterestName);
        //pointOfInterestName.setText("Berry Lane Property 5");
        pointOfInterestName.setText(replyObject.getString("pointOfInterestName"));
        TextView eventBody = (TextView) findViewById(R.id.eventBody);
        //eventBody.setText("EPA has selected the Jersey City Redevelopment Agency for three brownfields cleanup grants. Hazardous substances...");
        eventBody.setText(eBody);

        //testObject = new ParseObject("TestObject");
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
