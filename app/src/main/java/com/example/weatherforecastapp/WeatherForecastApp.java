package com.example.weatherforecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherForecastApp extends AppCompatActivity {
    String s;
    static TextView place;
    static TextView temp,max,min,des;
    static TextView[] day = new TextView[7];
    static TextView[] date = new TextView[7];
    static TextView[] maxT = new TextView[7];
    static TextView[] minT = new TextView[7];
    static TextView[] desF = new TextView[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast_app);
        Intent in = getIntent();
        s = in.getStringExtra("Info");
        place = (TextView)findViewById(R.id.cLoc);
        temp = (TextView)findViewById(R.id.cTemp);
        max = (TextView)findViewById(R.id.cMax);
        min = (TextView)findViewById(R.id.cMin);
        des = (TextView)findViewById(R.id.cDes);
        day[0] = (TextView)findViewById(R.id.day1);
        day[1] = (TextView)findViewById(R.id.day2);
        day[2] = (TextView)findViewById(R.id.day3);
        day[3] = (TextView)findViewById(R.id.day4);
        day[4] = (TextView)findViewById(R.id.day5);
        day[5] = (TextView)findViewById(R.id.day6);
        day[6] = (TextView)findViewById(R.id.day7);

        date[0] = (TextView)findViewById(R.id.date1);
        date[1] = (TextView)findViewById(R.id.date2);
        date[2] = (TextView)findViewById(R.id.date3);
        date[3] = (TextView)findViewById(R.id.date4);
        date[4] = (TextView)findViewById(R.id.date5);
        date[5] = (TextView)findViewById(R.id.date6);
        date[6] = (TextView)findViewById(R.id.date7);


        maxT[0] = (TextView)findViewById(R.id.max1);
        maxT[1] = (TextView)findViewById(R.id.max2);
        maxT[2] = (TextView)findViewById(R.id.max3);
        maxT[3] = (TextView)findViewById(R.id.max4);
        maxT[4] = (TextView)findViewById(R.id.max5);
        maxT[5] = (TextView)findViewById(R.id.max6);
        maxT[6] = (TextView)findViewById(R.id.max7);

        minT[0] = (TextView)findViewById(R.id.min1);
        minT[1] = (TextView)findViewById(R.id.min2);
        minT[2] = (TextView)findViewById(R.id.min3);
        minT[3] = (TextView)findViewById(R.id.min4);
        minT[4] = (TextView)findViewById(R.id.min5);
        minT[5] = (TextView)findViewById(R.id.min6);
        minT[6] = (TextView)findViewById(R.id.min7);

        desF[0] = (TextView)findViewById(R.id.des1);
        desF[1] = (TextView)findViewById(R.id.des2);
        desF[2] = (TextView)findViewById(R.id.des3);
        desF[3] = (TextView)findViewById(R.id.des4);
        desF[4] = (TextView)findViewById(R.id.des5);
        desF[5] = (TextView)findViewById(R.id.des6);
        desF[6] = (TextView)findViewById(R.id.des7);

        Weather getData = new Weather();
        getData.execute("http://api.openweathermap.org/data/2.5/"+
                "weather?q="+ s +"&units=metric&appid=c37f3551d980d0899f6e95a99a8a56c8");

    }
}