/**
 * Created by nitish on 08-04-2017.
 */
package com.example.android.quakereport;

public class Earthquake {

    // magnitude of earthquake
   //private String mMagnitude;
    private double mMagnitude;
    //
    private String mLocation;

    //private String mDate;
    private long mTimeInMilli;

    /** Website URL of the earthquake */
    private String mUrl;

    /**
     *Construct a new {@link Earthquake} object
     *
     * @param magnitude
     * @param location
     * @param //date
     */
    public Earthquake(double magnitude, String location, long timeInMilli, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilli = timeInMilli;
        mUrl = url;
    }

    /**
     * returns the magnitude of the earthquake
     */
    public double getMagnitude(){
        return mMagnitude;

    }

    public String getLocation(){
        return mLocation;

    }
    public long getTimeInMilli(){
        return mTimeInMilli;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }

}












