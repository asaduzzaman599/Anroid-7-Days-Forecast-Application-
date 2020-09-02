package com.example.weatherforecastapp;

import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherForecast extends AsyncTask<String,Void,String> {
    String result;
    int[] day = new int[7];
    int[] max = new int[7];
    int[] min = new int[7];
    String[] des = new String[7];
    String[] dt = new String[7];
    @Override
    protected String doInBackground(String... urls) {
        result="";
        URL link;
        HttpURLConnection myConnection = null;

        try{
            link = new URL(urls[0]);
            myConnection = (HttpURLConnection)link.openConnection();
            InputStream in = myConnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while(data != -1){
                char current = (char)data;
                result += current;
                data = myStreamReader.read();
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

        try{
            JSONObject myObject = new JSONObject(result);
            JSONArray jsonArray = myObject.getJSONArray("daily");
            // System.out.println(jsonArray);
            //System.out.println(jsonArray.length());
            for (int i=0;i<7;i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i+1);
                Long tempDate = jsonObject.getLong("dt");
                JSONObject temp = new JSONObject(jsonObject.getString("temp"));
                day[i] = roundValue(Float.parseFloat(temp.getString("day")));
                max[i] = roundValue(Float.parseFloat(temp.getString("max")));
                min[i] = roundValue(Float.parseFloat(temp.getString("min")));


                JSONArray weatherArray = jsonObject.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                des[i] = weather.getString("description");



                //System.out.println(day);
                //System.out.println(max);
                //System.out.println(min);


                // MainActivity.day[5].setText(day);


                long unixSeconds = tempDate;
                Date date = new java.util.Date(unixSeconds*1000L);
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
                dt[i] = sdf.format(date);


            }
/*
            System.out.println(dt[5]);
            System.out.println(max[5]);
            System.out.println(min[5]);

*/



            for (int i=0;i<7;i++){

                WeatherForecastApp.day[i].setText(day[i] +"\u2103");
                WeatherForecastApp.date[i].setText(dt[i].substring(8,10)+"/"+dt[0].substring(5,7));
                WeatherForecastApp.maxT[i].setText("MAX: " + max[i] +"\u2103");
                WeatherForecastApp.minT[i].setText("MIN: " + min[i] +"\u2103");
                WeatherForecastApp.desF[i].setText(des[i] );
            }


        }catch (JSONException e){
             e.printStackTrace();
        }
    }

    private int roundValue(float min) {
        if(min % 1 < .5)return  (int) min;
        return (int) min +1;

    }
}
