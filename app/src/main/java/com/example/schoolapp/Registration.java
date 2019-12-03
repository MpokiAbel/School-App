package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public  void myDate(View v){

      DatePickerFragment date =  new DatePickerFragment();
      date.show(getSupportFragmentManager(),"date Picker");
        EditText myCalendar =findViewById(R.id.Date);
        myCalendar.setText(date.update());
    }















}
