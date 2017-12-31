package com.example.pyk.multimedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Diary;
import sql.DiaryController;

public class MainActivity extends AppCompatActivity {
    //get your date
    public Integer yy, mm, dd;
    private int user_id;
    private final AppCompatActivity activity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        ********************
//        set your date to now
//        ********************
        DiaryController diaryController = new DiaryController(activity);
        List<Diary> diaryList = diaryController.getAllDiary(user_id);
        Log.d("di", "d" + diaryList.size());
        Date myDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        yy = calendar.get(Calendar.YEAR);
        mm = calendar.get(Calendar.MONTH) + 1;
        dd = calendar.get(Calendar.DATE); //Day of the month :)
        user_id = getIntent().getIntExtra("id", 0);
        Log.d(yy.toString(), "year:");
        Log.d(mm.toString(), "month:");
        Log.d(dd.toString(), "day:");

//        ********************
//        click add button
//        ********************
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_newdiary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                deliver data to intent
                Intent i = new Intent();
                i.setClass(MainActivity.this, NewDiaryActivity.class);
                Bundle date = new Bundle();
                date.putString("year", yy.toString());
                date.putString("month", mm.toString());
                date.putString("day", dd.toString());
                date.putInt("id", user_id);
                i.putExtras(date);
                startActivity(i);
                finish();
            }
        });

//        ********************
//        click Calender
//        ********************
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                yy = year;
                mm = month + 1;
                dd = dayOfMonth;
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
