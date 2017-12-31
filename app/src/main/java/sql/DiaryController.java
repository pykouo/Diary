package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Diary;
import model.User;

public class DiaryController extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Diary.db";

    // User table name
    private static final String TABLE_NAME = "diary";

    // User Table Columns names
    private static final String COLUMN_DIARY_ID = "diary_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_EMOTION = "emotion";
    private static final String COLUMN_WEATHER = "weather";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_USER_ID = "user_id";

    /**
     * Constructor
     *
     * @param context
     */
    public DiaryController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void add(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, diary.getDate());
        values.put(COLUMN_EMOTION, diary.getEmotion());
        values.put(COLUMN_WEATHER, diary.getWeather());
        values.put(COLUMN_CONTENT, diary.getContent());
        values.put(COLUMN_USER_ID, diary.getUser_id());

        // Inserting Row

        long rowInserted = db.insert(TABLE_NAME, null, values);
        Log.d("insert", "a" + rowInserted);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Diary> getAllDiary(int user_id) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_DIARY_ID,
                COLUMN_DATE,
                COLUMN_EMOTION,
                COLUMN_WEATHER,
                COLUMN_CONTENT,
                COLUMN_USER_ID
        };
        // sorting orders
        String sortOrder =
                COLUMN_DATE + " ASC";
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {Integer.toString(user_id)};

        List<Diary> diaryList = new ArrayList<Diary>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        Log.d("d", "a" + cursor.getCount());
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Diary diary = new Diary();
                diary.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_ID))));
                diary.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                diary.setEmotion(cursor.getString(cursor.getColumnIndex(COLUMN_EMOTION)));
                diary.setWeather(cursor.getString(cursor.getColumnIndex(COLUMN_WEATHER)));
                diary.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)));
                // Adding user record to list
                diaryList.add(diary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return diaryList;
    }

    public void updateDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, diary.getDate());
        values.put(COLUMN_EMOTION, diary.getEmotion());

        // updating row
        db.update(TABLE_NAME, values, COLUMN_DIARY_ID + " = ? AND " + COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(diary.getId()), String.valueOf(diary.getUser_id())});
        db.close();
    }


    public void deleteDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, COLUMN_DIARY_ID + " = ? AND " + COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(diary.getId()), String.valueOf(diary.getUser_id())});
        db.close();
    }

    /**
     * This method to check user exist or not
     */
    public Diary getDiary(Diary diary) {

        String[] columns = {
                COLUMN_DIARY_ID,
                COLUMN_DATE,
                COLUMN_EMOTION,
                COLUMN_WEATHER,
                COLUMN_CONTENT,
                COLUMN_USER_ID
        };
        String selection = COLUMN_DIARY_ID + " = ? AND " + COLUMN_USER_ID + " = ?";

        String[] selectionArgs = {Integer.toString(diary.getId()), Integer.toString(diary.getUser_id())};

        SQLiteDatabase db = this.getReadableDatabase();

        // query user table with conditions
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Diary diary1 = new Diary();
        if (cursorCount > 0) {
            cursor.moveToFirst();
            diary1.setId(cursor.getInt(0));
            diary1.setDate(cursor.getString(1));
            diary1.setEmotion(cursor.getString(2));
            diary1.setWeather(cursor.getString(3));
            diary1.setContent(cursor.getString(4));
        }
        cursor.close();
        db.close();
        return diary1;
    }
}