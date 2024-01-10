package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {
  EditText dateselect, timeselect, task_title_input, editTextTextMultiLine;
  TimePickerDialog timePickerDialog;
  DatePickerDialog.OnDateSetListener setListener;
  Calendar cal;
  String amPm;
  int currentHour;
  int currentMinutes;
  DbaseHelper dbaseHelper;
  RatingBar ratingBar;
  Button create_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dateselect = findViewById(R.id.editTextDate);
        ratingBar = findViewById(R.id.ratingBar);
        task_title_input = findViewById(R.id.task_title_input);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        create_task = findViewById(R.id.createTask_btn);
        dbaseHelper = new DbaseHelper(this);
        Calendar calendar = Calendar.getInstance();
        final  int  year = calendar.get(Calendar.YEAR);
        final  int  month = calendar.get(Calendar.MONTH);
        final  int  day = calendar.get(Calendar.DAY_OF_MONTH);

//inserting in the database
        create_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tasktitle = task_title_input.getText().toString();
                String date = dateselect.getText().toString();
                String time = timeselect.getText().toString();
                float rating = ratingBar.getRating();
                String description = editTextTextMultiLine.getText().toString();

                Boolean check_inserted_data = dbaseHelper.insertData(Tasktitle, date, time, rating, description);
                if(check_inserted_data == true){
                    Toast.makeText(CreateActivity.this, "Task created successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CreateActivity.this, "Sorry failed to create task", Toast.LENGTH_SHORT).show();
                }
            }
        });


//date selector
        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                dateselect.setText(date);

            }
        };
        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        dateselect.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });//datepicker


        // time picker
        timeselect = findViewById(R.id.editTextTime);
        timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cal = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinutes = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(CreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        }else {
                            amPm = "AM";
                        }
                        timeselect.setText(String.format("02d:02d", hourOfDay, minutes) + amPm);
                        timeselect.setText(hourOfDay+":"+minutes + amPm);
                    }
                }, currentHour, currentMinutes, false);
                timePickerDialog.show();
            }
        });// time picker
    }
}