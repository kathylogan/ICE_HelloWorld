package com.cgifederal.ice_helloworld;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Ayush on 8/12/2016.
 */
public class SecondaryPage extends Activity {
    ParseObject testObject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_page);

        Intent intent = getIntent();
        // set data based on Parse record
        TextView featureName = (TextView) findViewById(R.id.featureName);
        featureName.setText("Brownfield");
        TextView pointOfInterestName = (TextView) findViewById(R.id.pointOfInterestName);
        pointOfInterestName.setText("Berry Lane Property 5");
        TextView eventBody = (TextView) findViewById(R.id.eventBody);
        eventBody.setText("EPA has selected the Jersey City Redevelopment Agency for three brownfields cleanup grants. Hazardous substances...");


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
