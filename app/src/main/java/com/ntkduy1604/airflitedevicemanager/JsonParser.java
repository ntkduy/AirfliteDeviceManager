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

    public JSONArray makeGetHttpRequest(String vUrl) {
        JSONArray jsonArray = null;

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
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
//            Log.e("JSON Parser", "result: " + result.toString());             // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

        /**
         * Return the retrieved data as an Array Object
         */
        try {
            jsonArray = new JSONArray(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON Array
        return jsonArray;
    }

    public JSONObject makePostHttpRequest(String vUrl, JSONObject jsonObject) {
        JSONObject jsonObject1 = null;
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
//            Log.e("paramsString", paramsString);            // Debugger

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

        // try parse the string to a JSON object
        try {
            jsonObject1 = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jsonObject1;
    }

    public JSONObject makePutHttpRequest(String vUrl, JSONObject jsonObject) {
        JSONObject jsonObject1 = null;
        DataOutputStream dataOutputStream;
        String paramsString;

        try {
            urlObj = new URL(vUrl);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();
            paramsString = jsonObject.toString();
            Log.e("paramsString", paramsString);

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
            Log.e("JSON Parser", "result: " + result.toString());             // Debugger
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        conn.disconnect();

        // try parse the string to a JSON object
        try {
            jsonObject1 = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jsonObject1;
    }

    public void makeDeleteHttpRequest(String vUrl) {
        JSONObject jsonObject1 = null;

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

        // try parse the string to a JSON object
        try {
            jsonObject1 = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
    }
}
