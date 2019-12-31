package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registration_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__category);
    }


    public void move_student (View view){

        Intent i =new Intent(this,Registration.class);
        startActivity(i);


    }



    public void move_staff (View view){

        Intent i =new Intent(this,Staff_Registration.class);
        startActivity(i);


    }


}