package com.ntkduy1604.airflitedevicemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the JSON GET View
        TextView get_device_list = (TextView) findViewById(R.id.category_device_list_text_view);
        get_device_list.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                String message = "Open the list of " + getString(R.string.category_list_of_device);
                Toast.makeText(view.getContext(),message, Toast.LENGTH_SHORT).show();
                Intent getDeviceList = new Intent(MainActivity.this, CategoryDeviceList.class);
                startActivity(getDeviceList);
            }
        });
    }
}
