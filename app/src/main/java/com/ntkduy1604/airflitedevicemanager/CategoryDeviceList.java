package com.ntkduy1604.airflitedevicemanager;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryDeviceList extends AppCompatActivity {

    //    private String TAG = MainActivity.class.getSimpleName(); //For debugging purpose
    private ListView listView;              // Create a ListView variable
    private ArrayList<Device> devices;          // Add array of Words To JsonGet
    private DeviceAdapter itemsAdapter;       // Create a DeviceAdapter item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_device_list);

        devices = new ArrayList<Device>();
        listView = (ListView) findViewById(R.id.activity_device_list);
        itemsAdapter = new DeviceAdapter(CategoryDeviceList.this, devices, R.color.category_device_list);

        new getDeviceList().execute();

    }

    private class getDeviceList extends AsyncTask<Void, Void, Void> {
        String mId, mName, mModel, mSerialno, mActivedate;

        JsonParser jsonParser = new JsonParser();

        private static final String LOGIN_URL = "https://tadac.com.au/airflite/api1/1/devices";

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        @Override
        protected Void doInBackground(Void... arg0) {
            String temp;


            try {
                Log.d("request", "starting");

                String jsonString = jsonParser.makeGetHttpRequest(LOGIN_URL);
                JSONArray jsonArray;
                jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    temp = jsonObject.getString("id"); mId = temp;
                    temp = jsonObject.getString("name"); mName = temp;
                    temp = jsonObject.getString("model"); mModel = temp;
                    temp = jsonObject.getString("serialno"); mSerialno = temp;
                    temp = jsonObject.getString("activedate"); mActivedate = temp;
//                        temp = jsonObject.getString("userid");         mUserid = temp;
//                        temp = jsonObject.getString("comid");           mComid = temp;

//                        temp = jsonObject.getString("tagid");           mTagid = temp;

                    devices.add(new Device(mId, mName, mModel, mSerialno, mActivedate,
//                                mUserid, mComid,   mTagid,
                            R.mipmap.ic_launcher));
                    itemsAdapter = new DeviceAdapter(CategoryDeviceList.this, devices, R.color.category_device_list);
                }

//                Log.e("JSON result", jsonArray.toString());           // Debugger

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            listView.setAdapter(itemsAdapter);
        }
    }
}
