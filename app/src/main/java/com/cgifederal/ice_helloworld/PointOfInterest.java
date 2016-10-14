package com.cgifederal.ice_helloworld;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ayush on 8/11/2016.
 */
@ParseClassName("PointOfInterest")
public class PointOfInterest extends ParseObject{

    public String getID(){
        return getString("objectId");
    }

    public String getLocation(){
        return getString("location");
    }

    public void setLocation(String location){ put("location", location);}

    public String getParseName(){
        return getString("name");
    }

    public void setParseName(String name){ put("name", name);}

    public String getFeature(){
        return getString("Feature");
    }

    public void setFeature(String feature){put("Feature", feature);}
}
