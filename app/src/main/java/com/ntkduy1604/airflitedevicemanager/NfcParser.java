package com.ntkduy1604.airflitedevicemanager;

import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Parcelable;

import static android.R.attr.type;

/**
 * Created by NTKDUY on 3/1/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

public class NfcParser {
    private Tag tag;

    public NfcParser(Parcelable parcelable){
        tag = (Tag) parcelable;
    }

    public String getNfcTagId (){
        byte[] id       = tag.getId();
        return getHex(id);
    }

    public String getNfcTechnology(){
        String[] nfcTech            = tag.getTechList();
        StringBuilder stringBuilder = new StringBuilder();
        String prefix = "android.nfc.tech.";
        for (String tech : nfcTech) {
            stringBuilder.append(tech.substring(prefix.length()));
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private String getHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                stringBuilder.append('0');
            stringBuilder.append(Integer.toHexString(b));
            if (i > 0) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
