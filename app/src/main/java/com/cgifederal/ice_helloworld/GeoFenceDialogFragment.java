package com.cgifederal.ice_helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseObject;

public class GeoFenceDialogFragment extends DialogFragment {
    String pointOfInterestId;


    public GeoFenceDialogFragment() {
    }
    // TODO: avoid non-default constructors
    public GeoFenceDialogFragment(ParseObject poi) {
        pointOfInterestId = poi.getObjectId();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You have entered the Brownsville cleanup site!")
                .setTitle("Fence Encountered")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getActivity(), SecondaryPage.class);
                        intent.putExtra(Intent.EXTRA_TEXT, pointOfInterestId);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}
