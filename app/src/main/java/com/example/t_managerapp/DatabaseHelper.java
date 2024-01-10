package com.example.t_managerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(name TEXT, email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int ii) {
        MyDatabase.execSQL("drop Table if exists allusers");

    }

    public Boolean insertData(String name, String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkname(String name){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where name = ?", new String[]{name});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkemail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checknameemail(String name, String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where name = ? and email = ?", new String[]{name, email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checknameemailpassword(String name, String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where name = ? and email = ? and password = ?", new String[]{name, email, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkpassword(String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery( "Select * from allusers where password = ?", new String[]{ password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean updatepassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});
        if (cursor.getCount()>0){
        long result = MyDatabase.update("allusers", contentValues, "email = ?", new String[]{email} );
        if (result == -1){
            return false;
        }else {
            return true;
        }
        }else {
            return false;
        }
    }
}





