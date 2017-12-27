package com.example.pyk.multimedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by pyk on 12/26/17.
 */

public class NewDiaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdiary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set mood list ( spinner list item )

        String[] str_mood = {"Anger", "Contempt", "Disgust", "Fear", "Happiness", "Sadness", "Surprise", "Neutral"};
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Spinner spinner_mood = (Spinner) findViewById(R.id.spinner_mood);
        spinner_mood.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_mood));

        String[] str_weather = {"Sunny", "Cloudy", "Windy", "Rainy"};
        Spinner spinner_weather = (Spinner) findViewById(R.id.spinner_weather);
        spinner_weather.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_weather));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
