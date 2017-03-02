package com.ntkduy1604.airflitedevicemanager;

/**
 * Created by NTKDUY on 2/26/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class inherits all the properties of ArrayAdapter
 * but it will extend ArrayAdapter class to support double TextView
 * for a particular data type Device (custom class)
 */
public class DeviceSingleAdapter extends ArrayAdapter<Device> {

    public DeviceSingleAdapter(Activity context, ArrayList<Device> device){
        super(context, 0, device);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.device_single_info, parent, false);
        }

        // Get the {@link Device} object located at this position in the list
        Device currentDevice = getItem(position);

        TextView tagIdTextView = (TextView) listItemView.findViewById(R.id.nfc_scanned_tag_id_tv);
        tagIdTextView.setText(currentDevice.getTagid());

        final TextView nameTextView = (TextView) listItemView.findViewById(R.id.nfc_scanned_name_tv);
        final EditText nameEditText = (EditText) listItemView.findViewById(R.id.nfc_scanned_name_et);
        nameTextView.setText(currentDevice.getName());

        final TextView modelTextView = (TextView) listItemView.findViewById(R.id.nfc_scanned_model_tv);
        final EditText modelEditText = (EditText) listItemView.findViewById(R.id.nfc_scanned_model_et);
        modelTextView.setText(currentDevice.getModel());

        final TextView serialnoTextView = (TextView) listItemView.findViewById(R.id.nfc_scanned_serial_no_tv);
        final EditText serialEditText = (EditText) listItemView.findViewById(R.id.nfc_scanned_serial_no_et);
        serialnoTextView.setText(currentDevice.getSerialno());

        TextView activedateTextView = (TextView) listItemView.findViewById(R.id.nfc_scaned_activate_date_tv);
        activedateTextView.setText(currentDevice.getActivedate());

        nameTextView.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                nameTextView.setVisibility(View.GONE);
                nameEditText.setVisibility(View.VISIBLE); //or switcher.showPrevious();
            }
        });

        modelTextView.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                modelTextView.setVisibility(View.GONE);
                modelEditText.setVisibility(View.VISIBLE); //or switcher.showPrevious();
            }
        });

        serialnoTextView.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                serialnoTextView.setVisibility(View.GONE);
                serialEditText.setVisibility(View.VISIBLE); //or switcher.showPrevious();
            }
        });

        return listItemView;
    }

}
