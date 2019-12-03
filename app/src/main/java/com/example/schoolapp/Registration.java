package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

public class Registration extends AppCompatActivity {

         EditText calendar;
         Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        calendar =(EditText)findViewById(R.id.Date);
        c=Calendar.getInstance();

      final   DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener(){


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update();
            }
            };

        calendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Registration.this, date, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }


    public void update(){

        String format="dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        calendar.setText(sdf.format(c.getTime()));

    }
















}
