package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;


public class DBManager {

    private myDbHandler dbHandler;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }


    public DBManager open() throws SQLException {
        dbHandler = new myDbHandler(context);
        database = dbHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHandler.close();
    }

    public void insert(String RegNO, String fname, String mname, String lname, String DateOfbirth,
                       String Email, String PhoneNumber, String region, String district, String ward, String studyYear,
                       String semister, String gender) {

        ContentValues contentValue = new ContentValues();

        contentValue.put(dbHandler.RegNO, RegNO);
        contentValue.put(dbHandler.fname, fname);
        contentValue.put(dbHandler.mname, mname);
        contentValue.put(dbHandler.lname, lname);
        contentValue.put(dbHandler.DateOfbirth, DateOfbirth);
        contentValue.put(dbHandler.Email, Email);
        contentValue.put(dbHandler.region, region);
        contentValue.put(dbHandler.district, district);
        contentValue.put(dbHandler.ward,ward);
        contentValue.put(dbHandler.studyYear, studyYear);
        contentValue.put(dbHandler.semister, semister);
        contentValue.put(dbHandler.gender, gender);
        database.insert(dbHandler.STUDENT_TABLE, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbHandler.RegNO, dbHandler.fname, dbHandler.mname, dbHandler.lname,
        dbHandler.DateOfbirth, dbHandler.Email, dbHandler.PhoneNumber, dbHandler.region, dbHandler.district,
        dbHandler.ward,dbHandler.studyYear, dbHandler.semister, dbHandler.gender};


        Cursor cursor = database.query(dbHandler.STUDENT_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(String RegNO, String fname, String mname, String lname, String DateOfbirth,
                      String Email, String PhoneNumber, String region, String district, String ward, String studyYear,
                      String semister, String gender) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHandler.RegNO, RegNO);
        contentValues.put(dbHandler.fname, fname);
        contentValues.put(dbHandler.mname, mname);
        contentValues.put(dbHandler.lname, lname);
        contentValues.put(dbHandler.DateOfbirth, DateOfbirth);
        contentValues.put(dbHandler.Email, Email);
        contentValues.put(dbHandler.PhoneNumber, PhoneNumber);
        contentValues.put(dbHandler.region, region);
        contentValues.put(dbHandler.district, district);
        contentValues.put(dbHandler.ward,ward);
        contentValues.put(dbHandler.studyYear, studyYear);
        contentValues.put(dbHandler.semister, semister);
        contentValues.put(dbHandler.gender, gender);


        int i = database.update(dbHandler.STUDENT_TABLE, contentValues, dbHandler.RegNO+ " = " + RegNO, null);
        return i;
    }


    public void delete(String RegNo) {
        database.delete(dbHandler.STUDENT_TABLE, dbHandler.RegNO+ "=" + RegNo, null);
    }

}
