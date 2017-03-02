package com.ntkduy1604.airflitedevicemanager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NTKDUY on 3/1/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

public class JsonParser {
    private String charset = "UTF-8";
    private URL urlObj;
    private HttpURLConnection conn;
    private StringBuilder result;

    public String makeGetHttpRequest(String vUrl) {
        try {
            urlObj = new URL(vUrl);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setConnectTimeout(15000);
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Receive the response from the server
         */
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) result.append(line);
//            Log.e("JSON Parser", "result: " + result.toString());             // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

        // return JSON Array
        return result.toString();
    }

    public String makePostHttpRequest(String vUrl, JSONObject jsonObject) {
        DataOutputStream dataOutputStream;
        String paramsString;

        try {
            urlObj = new URL(vUrl);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();
            paramsString = jsonObject.toString();
            Log.e("paramsString", paramsString);            // Debugger

            dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeBytes(paramsString);
            dataOutputStream.flush();
            dataOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Receive the response from the server
         */
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
//            Log.e("JSON Parser", "result: " + result.toString());             // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

        // return JSON Object
        return result.toString();
    }

    public String makePutHttpRequest(String vUrl, JSONObject jsonObject) {
        DataOutputStream dataOutputStream;
        String paramsString;

        try {
            urlObj = new URL(vUrl);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            paramsString = jsonObject.toString();
            Log.e("paramsString", paramsString);
            conn.connect();

            dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeBytes(paramsString);
            dataOutputStream.flush();
            dataOutputStream.close();
            Log.e("write ", "done");
            conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Receive the response from the server
         */
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Log.e("JSON Parser", "result: " + result.toString());
                result.append(line);
            }
                        // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

        // return JSON Object
        return result.toString();
    }

    public void makeDeleteHttpRequest(String vUrl) {
        try {
            urlObj = new URL(vUrl);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Receive the response from the server
         */
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
//            Log.e("JSON Parser", "result: " + result.toString());             // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

    }
}
