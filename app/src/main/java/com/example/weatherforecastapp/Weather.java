package com.example.weatherforecastapp;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather extends AsyncTask<String,Void,String> {
    String result;
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
            JSONObject coord = new JSONObject(myObject.getString("coord"));
            String lon = coord.getString("lon");
            String lat = coord.getString("lat");


            JSONArray jsonArray = myObject.getJSONArray("weather");
            JSONObject weather = jsonArray.getJSONObject(0);
            String des = weather.getString("description");

            JSONObject main = new JSONObject(myObject.getString("main"));
            String temperature = main.getString("temp");
            String min = main.getString("temp_min");
            String max = main.getString("temp_max");
            String placeName = myObject.getString("name");
           /* String max = main.getString("temp_max");
            String min = main.getString("temp_min");*/
            System.out.println(des);


            WeatherForecastApp.place.setText(placeName);
            WeatherForecastApp.temp.setText(roundValue(Float.parseFloat(temperature)) + "\u2103");
            WeatherForecastApp.max.setText("MAX: "+roundValue(Float.parseFloat(max))+ "\u2103");
            WeatherForecastApp.min.setText("MIN: " + roundValue(Float.parseFloat(min))+ "\u2103");
            WeatherForecastApp.des.setText(des);
            // MainActivity.des.setText(des);

            WeatherForecast getData = new WeatherForecast();
            getData.execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon +
                    "&exclude=hourly,minutely&units=metric&appid=c37f3551d980d0899f6e95a99a8a56c8");

        }catch (JSONException e){
            e.printStackTrace();

        }
    }
    private int roundValue(float min) {
        if(min % 1 < .5)return  (int) min;
        return (int) min +1;

    }
}
