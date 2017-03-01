package com.ntkduy1604.airflitedevicemanager;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.TextView;

public class CategoryNfcReader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_nfc_reader);

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
        String deviceTagId = handleIntent(getIntent());

        TextView nfcTagIdTextView = (TextView) findViewById(R.id.nfc_scanned_tag_id_tv);
        nfcTagIdTextView.setText(deviceTagId);
    }

    private String handleIntent(Intent intent) {
        String action = intent.getAction();
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
                return nfcParser.getNfcTagId();
            }
        }
        return null;
    }
}
