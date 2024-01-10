package com.example.t_managerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class DbaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Task_list.db";
    public DbaseHelper( Context context) {
        super(context, "Task_list.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase task_database) {
        task_database.execSQL("create Table task_list(task_title TEXT primary key, date TEXT , time TEXT, ratings REAL, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase task_database, int i, int i1) {
        task_database.execSQL("drop table if exists task_list");

    }

    public Boolean insertData(String task_title, String date, String time, float ratings, String description){
        SQLiteDatabase task_database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task_title", task_title);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("ratings", ratings);
        contentValues.put("description", description);
        long result = task_database.insert("task_list", null, contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase task_database = this.getWritableDatabase();
        Cursor cursor = task_database.rawQuery( "Select * from task_list ",null);
        return cursor;
    }



}
