package com.technocurl.www.parsejson;

/**
 * Created by dinesh on 9/11/16.
 */
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by deadlydragger on 6/26/16.
 */
public class HttpUrlConnectionJson {

   /* HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
    HttpConnectionParams.setSoTimeout(httpParameters, 10000);*/

    private static final String TAG = "dinesh";

    public static String sendHTTPData(String urlpath, JSONObject json) {
        HttpURLConnection connection = null;
        try {
            URL url=new URL(urlpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(json.toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                Log.d(TAG, stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                Log.e(TAG, connection.getResponseMessage());
                return null;
            }
        }
        catch (Exception exception){
            Log.e(TAG, exception.toString());
            return null;
        }/* catch (SocketTimeoutException e){
            if(connection != null){
                connection.disconnect();
            }
            return null;
        }*/
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }
}
