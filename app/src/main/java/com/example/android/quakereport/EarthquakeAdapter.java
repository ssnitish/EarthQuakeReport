package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nitish on 08-04-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{


    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param //resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param earthquakes  The objects to represent in the ListView.
     */
    public EarthquakeAdapter(@NonNull Context context,  @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * returns a list item view that displays info about the earthquake at the given position in the
     * list of earthquake
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    // check if there is there any existing list item view (ca lled convertView) that we can reuse
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item,parent,false);
        }

        //find the earth quake at the given position
        Earthquake currentEarthquake = getItem(position);

        //Find the TextView with view Id magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // display the magnitude of current earthquake in that TextView
        // magnitudeView.setText((int) currentEarthquake.getMagnitude());// no error, after re-declaring magnitude as double
       // magnitudeView.setText(currentEarthquake.getMagnitude()); // error -cannot resolve setText(double)

        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



      //  TextView locationView = (TextView) listItemView.findViewById(R.id.location);
       // locationView.setText(currentEarthquake.getLocation());
        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        //Once we have the 2 separate Strings, we can display them in the 2 TextViews in the list item layout.

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake

        Date dateObject = new Date(currentEarthquake.getTimeInMilli());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);
       // dateView.setText((int) currentEarthquake.getTimeInMilli()); // why typecasting required when changed to long

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        // return the list item view that is now showing the appropiate date
        return listItemView;



    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     Return the color for the magnitude circle based on the intensity of the earthquake.

     @param magnitude of the earthquake
         */

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }




}


