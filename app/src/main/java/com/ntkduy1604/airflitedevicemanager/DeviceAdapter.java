package com.ntkduy1604.airflitedevicemanager;

/**
 * Created by NTKDUY on 2/26/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class inherits all the properties of ArrayAdapter
 * but it will extend ArrayAdapter class to support double TextView
 * for a particular data type Device (custom class)
 */
public class DeviceAdapter extends ArrayAdapter<Device> {

    private int mImageResourceId;

    public DeviceAdapter(Activity context, ArrayList<Device> device, int vImageResourceId){
        super(context, 0, device);
        mImageResourceId = vImageResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.device_list_infor, parent, false);
        }

        // Get the {@link Device} object located at this position in the list
        Device currentDevice = getItem(position);

        TextView idTextView = (TextView) listItemView.findViewById(R.id.id_text_view);
        idTextView.setText(currentDevice.getId());

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        nameTextView.setText(currentDevice.getName());

        TextView modelTextView = (TextView) listItemView.findViewById(R.id.model_text_view);
        modelTextView.setText(currentDevice.getModel());

        TextView serialnoTextView = (TextView) listItemView.findViewById(R.id.serial_no_text_view);
        serialnoTextView.setText(currentDevice.getSerialno());

        TextView activatedateTextView = (TextView) listItemView.findViewById(R.id.active_date_text_view);
        activatedateTextView.setText(currentDevice.getActivedate());

        // Find the ImageView in the device_list_infor.xmlnfor.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        // Check if an image is provided for this word or not
        if (currentDevice.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentDevice.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        View textContainer = (View) listItemView.findViewById(R.id.text_container);
        View textDisplay = (View) listItemView.findViewById(R.id.text_view);
        int color = ContextCompat.getColor(getContext(), mImageResourceId);
        textContainer.setBackgroundColor(color);
        textDisplay.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
