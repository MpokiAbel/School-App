package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class schoolAppDB extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE="STUDENTS";

    public static final String ID="ID";
    public static final String REGISTRATION_NUMBER="REGISTRATION_NUMBER";
    public static final String FIRST_NAME="FIRST_NAME";
    public static final String MIDDLE_NAME="MIDDLE_NAME";
    public static final String LAST_NAME="LAST_NAME";
    public static final String DOB="DATE_OF_BIRTH";
    public static final String EMAIL="EMAIL";
    public static final String PHONE_NUMBER="PHONE_NUMBER";
    public static final String REGION="REGION";
    public static final String DISTRICT="DISTRICT";
    public static final String WARD="WARD";
    public static final String STUDY_YEAR="STUDY_YEAR";
    public static final String SEMESTER="SEMESTER";
    public static final String GENDER="GENDER";





    public schoolAppDB(@Nullable Context context) {


        super(context, "SCHOOL APP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+STUDENT_TABLE+"("+ID+" INTERGER PRIMARY KEY AUTOINCREMENT ,"+REGISTRATION_NUMBER+" TEXT NOT NULL,"+FIRST_NAME+" TEXT,"+MIDDLE_NAME+" TEXT,"+LAST_NAME+"" +
                " TEXT,"+DOB+" TEXT,"+EMAIL+" TEXT,"+PHONE_NUMBER+" TEXT,"+REGION+" TEXT,"+DISTRICT+" TEXT,"+WARD+" TEXT,"+STUDY_YEAR+" TEXT,"+SEMESTER+" TEXT,"+GENDER+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_TABLE);
        onCreate(db);
    }

    public Boolean insert(String regno , String fname , String mname , String lname , String date, String myemail , String mobileno , String region, String district,
    String ward, String studyyear, String semester, String gender){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues myValues = new ContentValues();

        myValues.put(REGISTRATION_NUMBER,regno);
        myValues.put(FIRST_NAME,fname);
        myValues.put(MIDDLE_NAME,mname);
        myValues.put(LAST_NAME,lname);
        myValues.put(DOB,date);
        myValues.put(EMAIL,myemail);
        myValues.put(PHONE_NUMBER,mobileno);
        myValues.put(REGION,region);
        myValues.put(DISTRICT,district);
        myValues.put(WARD,ward);
        myValues.put(STUDY_YEAR,studyyear);
        myValues.put(SEMESTER,semester);
        myValues.put(GENDER,gender);

        long ins= db.insert(STUDENT_TABLE,null,myValues);

        if (ins>0)return true;
        else return false;

    }





}
