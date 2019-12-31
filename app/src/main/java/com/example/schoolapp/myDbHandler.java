package com.example.schoolapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class myDbHandler extends SQLiteOpenHelper

{

    private static final int DATABASE_VERSION =1;

    //Database information

    private static final String DATABASE_NAME="School App.db";

    // Table Names
    public static final String STUDENT_TABLE = "STUDENTS";
    public static final String STAFF_TABLE = "STAFF";

    //Database  Students columns
    public static final String RegNO="REGISTARTION NUMBER";
    public static final String fname="FIRST NAME";
    public static final String mname="MIDDLE NAME";
    public static final String lname="LAST NAME";
    public static final String DateOfbirth="DATE OF BIRTH";
    public static final String Email="EMAIL";
    public static final String PhoneNumber="PHONE NUMBER";
    public static final String region="REGION";
    public static final String district="DISTRICT";
    public static final String ward ="WARD";
    public static final String studyYear="STUDY YEAR";
    public static final String semister="SEMISTER";
    public static final String gender ="GENDER";
    public static final String category="CATEGORY";









    public myDbHandler(@Nullable Context context) {

        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT_TABLE);
        db.execSQL(STAFF_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(myDbHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS table1");
        db.execSQL("DROP TABLE IF EXISTS table2");

        onCreate(db);
    }


    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }
}
