package com.example.schoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;





public class course_manager extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    String Regno;
    schoolAppDB myDB;
    Intent intent;
    Bundle bundle;
    TextView header;
    String fname,mname,lname,dob,email,phoneno,region,district,ward;
    String fname2,mname2,lname2;
    char fname1,mname1,lname1;
    SQLiteDatabase myDB2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_manager);

        myDB = new schoolAppDB(this);



        //receiving data from LogIn
        intent = getIntent();
        Regno = intent.getStringExtra("Registration_number");

        Cursor cursor=data(Regno);


        if (cursor.getCount()==0){

            Toast.makeText(course_manager.this,"Cursor is zero",Toast.LENGTH_SHORT);
        }
        else {
                while (cursor.moveToNext()){

                    fname=cursor.getString(0);
                    mname=cursor.getString(1);
                    lname=cursor.getString(2);
                    dob=cursor.getString(3);
                    email=cursor.getString(4);
                    phoneno=cursor.getString(5);
                    region=cursor.getString(6);
                    district=cursor.getString(7);
                    ward=cursor.getString(8);



                }

        }

        fname1=Character.toUpperCase(fname.charAt(0));
        mname1=Character.toUpperCase(mname.charAt(0));
        lname1=Character.toUpperCase(lname.charAt(0));

        fname2=fname1 + fname.substring(1);
        mname2=mname1 + mname.substring(1);
        lname2=lname1 + lname.substring(1);






        //Setting Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigation_status();


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Home_Fragment()).commit();


    }


    public void navigation_status() {


        navigationView = findViewById(R.id.navigationView);

        View headerView = navigationView.getHeaderView(0);
        TextView username =headerView.findViewById(R.id.userid);
        username.setText(lname2+" "+fname2+"("+Regno+")");
        username.setTextSize(18);
        username.setTypeface(Typeface.SERIF);







        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Home_Fragment()).commit();
                        break;

                    case R.id.nav_changePassword:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Password_Fragment()).commit();
                        break;
                    case R.id.nav_details:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new detail_Fragment()).commit();
                        break;
                    case R.id.nav_registration:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new course_Fragment()).commit();
                        break;
                    case R.id.nav_logout:

                        Intent intent = new Intent(course_manager.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }


    public Cursor data(String Regno)throws CursorIndexOutOfBoundsException {

        SQLiteDatabase db = myDB.getReadableDatabase();

        Cursor cursor =db.rawQuery("SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,DATE_OF_BIRTH,EMAIL,PHONE_NUMBER,REGION,DISTRICT,WARD FROM STUDENTS WHERE REGISTRATION_NUMBER =?",new String[]{Regno});

        return cursor;
    }

   public String[] getData(){

        String []values={fname2,mname2,lname2};
        return values;
   }





    public Cursor password(String regno){

        myDB2 = myDB.getReadableDatabase();

        Cursor cursor =myDB2.rawQuery("SELECT PASSWORD FROM STUDENTS WHERE REGISTRATION_NUMBER =?",new String[]{regno});

        return cursor;

    }

}







