package com.ntkduy1604.airflitedevicemanager;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CategoryNfcReader extends AppCompatActivity {

    private ListView listView;              // Create a ListView variable
    private ArrayList<Device> devices;          // Add array of Words To JsonGet
    private DeviceSingleAdapter itemsAdapter;       // Create a DeviceAdapter item

    private String TAG = CategoryNfcReader.class.getSimpleName(); //For debugging purpose
    private String APISERVER = "https://tadac.com.au/airflite/api1/1/devices/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_nfc_reader);

        devices = new ArrayList<Device>();
        listView = (ListView) findViewById(R.id.single_device_view);
        itemsAdapter = new DeviceSingleAdapter(CategoryNfcReader.this, devices);

        /**
         * Get NFC object
         */
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        /**
         * Device doesn't support NFC
         */
        if (nfcAdapter == null) {
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;         // Stop here, we definitely need NFC
        }

        /**
         * Device supports NFC, but check whether NFC is turned on
         */
        TextView nfcOnOffStatus = (TextView) findViewById(R.id.nfc_on_off_status);
        if (!nfcAdapter.isEnabled()) {
            nfcOnOffStatus.setText(R.string.nfc_disabled);
        } else {
            nfcOnOffStatus.setText(R.string.nfc_enable);
        }

        //Main task
        handleIntent(getIntent());

    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        String deviceTagId = null;
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            } else {
                Parcelable tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                NfcParser nfcParser = new NfcParser(tag);
                deviceTagId = nfcParser.getNfcTagId();
                Log.e(TAG, "nfcTagId: " + deviceTagId);

                new getDeviceStatus().execute(deviceTagId);

            }
        }
    }

    private class getDeviceStatus extends AsyncTask<String, Void, Void> {
        String nfcId, nfcName, nfcModel, nfcSerial, nfcTagId, nfcActiveDate;

        @Override
        protected void onPreExecute() {
            nfcId = nfcName = nfcModel = nfcSerial = nfcActiveDate = "unknown";
        }

        @Override
        protected Void doInBackground(String... args) {

            nfcTagId = args[0];
            String deviceJsonAddress = APISERVER + args[0];
            URI uri = null;

            /**
             * Encode the address
             */
            try {
                uri = new URI(deviceJsonAddress.replaceAll(" ", "%20"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            deviceJsonAddress = String.valueOf(uri);
            Log.e(TAG, "deviceJsonAddress: " + deviceJsonAddress);

            try {
                String jsonString = null;
                JsonParser jsonParser = new JsonParser();
                jsonString = jsonParser.makeGetHttpRequest(deviceJsonAddress);

                if (jsonString.contentEquals("No records found")) {
                    Log.e(TAG, "jsonArray: " + "Device not found");
                } else {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    Log.e(TAG, "jsonArray: " + "Device found");
                    nfcId = jsonObject.getString("id");
                    nfcName = jsonObject.getString("name");
                    nfcModel = jsonObject.getString("model");
                    nfcSerial = jsonObject.getString("serialno");
                    nfcActiveDate = jsonObject.getString("activedate");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            devices.add(new Device(nfcId, nfcName, nfcModel, nfcSerial, nfcTagId, nfcActiveDate));
            itemsAdapter = new DeviceSingleAdapter(CategoryNfcReader.this, devices);
            listView.setAdapter(itemsAdapter);
        }
    }
}
