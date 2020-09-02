package com.example.weatherforecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.etb);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cityName.getText().toString().isEmpty()) {
                    String s = cityName.getText().toString();
                    Intent in = new Intent(MainActivity.this, WeatherForecastApp.class);
                    in.putExtra("Info" , s);
                    startActivity(in);

                } else {
                    cityName.setError("Enter A City Name");
                }


            }

        });
    }
}