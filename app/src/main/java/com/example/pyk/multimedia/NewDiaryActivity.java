package com.example.pyk.multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by pyk on 12/26/17.
 */

public class NewDiaryActivity extends AppCompatActivity {
    String yy, mm, dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdiary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get date data
        Bundle bundle = getIntent().getExtras();
        yy = bundle.getString("year");
        mm = bundle.getString("month");
        dd = bundle.getString("day");
        String date = yy + "/" + mm + "/" + dd;
        //set date to textview
        final TextView text_date = (TextView) findViewById(R.id.text_time);
        text_date.setText(date);

//        Log.d(yy,"newwwww year:");
        //mood dropdown list
        String[] str_mood = {"Anger", "Contempt", "Disgust", "Fear", "Happiness", "Sadness", "Surprise", "Neutral"};
        Spinner spinner_mood = (Spinner) findViewById(R.id.spinner_mood);
        spinner_mood.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_mood));

        //weather dropdown list
        String[] str_weather = {"Sunny", "Cloudy", "Windy", "Rainy"};
        Spinner spinner_weather = (Spinner) findViewById(R.id.spinner_weather);
        spinner_weather.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_weather));

        //submit diary content(this could be change the content or create a new one)
        Button btnLogin = findViewById(R.id.btn_submitdiary);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText text_diary = (EditText) findViewById(R.id.text_diary);
                //send text_diary to database and stored it
                Log.d("diary content",text_diary.getText().toString());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
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
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
