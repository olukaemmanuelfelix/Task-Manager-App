package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> task_title, description, date, time, priority;
DbaseHelper dbaseHelper;
MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbaseHelper = new DbaseHelper(this);
        task_title = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        priority = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        adapter = new MyAdapter(this, task_title, description, date, time, priority);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = dbaseHelper.getdata();
        if (cursor.getCount() == 0 ){
            Toast.makeText(ViewActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                task_title.add(cursor.getString(0));
                description.add(cursor.getString(1));
                date.add(cursor.getString(2));
                time.add(cursor.getString(3));
                priority.add(cursor.getString(4));
            }
        }
    }

}