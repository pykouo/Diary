package com.example.pyk.multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import model.Diary;
import sql.DiaryController;

/**
 * Created by pyk on 12/26/17.
 */

public class NewDiaryActivity extends AppCompatActivity {
    String yy, mm, dd;
    int user_id;
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdiary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity = NewDiaryActivity.this;
        //get date data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            yy = bundle.getString("year");
            mm = bundle.getString("month");
            dd = bundle.getString("day");
            user_id = bundle.getInt("user_id");
        }
        String date = yy + "/" + mm + "/" + dd;
        //set date to textview
        final TextView text_date = findViewById(R.id.text_time);
        text_date.setText(date);

//        Log.d(yy,"newwwww year:");
        //mood dropdown list
        String[] str_mood = {"Anger", "Contempt", "Disgust", "Fear", "Happiness", "Sadness", "Surprise", "Neutral"};
        final Spinner spinner_mood = findViewById(R.id.spinner_mood);
        spinner_mood.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_mood));

        //weather dropdown list
        String[] str_weather = {"Sunny", "Cloudy", "Windy", "Rainy"};
        final Spinner spinner_weather = findViewById(R.id.spinner_weather);
        spinner_weather.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str_weather));
        //content
        final EditText text_diary = findViewById(R.id.text_diary);
        //submit diary content(this could be change the content or create a new one)
        Button btnSubmit = findViewById(R.id.btn_submitdiary);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Diary diary = new Diary();
                diary.setDate(text_date.getText().toString());
                diary.setEmotion(spinner_mood.getSelectedItem().toString());
                diary.setWeather(spinner_weather.getSelectedItem().toString());
                diary.setContent(text_diary.getText().toString());
                diary.setUser_id(user_id);

                DiaryController diaryController = new DiaryController(activity);
                diaryController.add(diary);
                //send text_diary to database and stored it
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
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
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
