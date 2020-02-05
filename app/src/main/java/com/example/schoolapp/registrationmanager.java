package com.example.schoolapp;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class registrationmanager extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar2;
    private NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationmanager);

        toolbar2 = findViewById(R.id.regtoolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigation_status2();


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                new studentreg()).commit();
        setTitle("Student Registration");


    }




    public void navigation_status2() {


        navigationView = findViewById(R.id.navigationView2);

/*        View headerView = navigationView.getHeaderView(0);
        TextView username =headerView.findViewById(R.id.userid);
        //username.setText(lname2+" "+fname2+"("+Regno+")");
        username.setTextSize(18);
        username.setTypeface(Typeface.SERIF);
*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.reg_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new studentreg()).commit();
                        setTitle("Student Registration");
                        break;

                    case R.id.staff_registration:
                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new staffreg()).commit();
                        setTitle("Staff Registration");

                        break;
                    case R.id.course_reg:
                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new coursereg()).commit();
                        setTitle("Course Registration");
                        break;
                    case R.id.nav_TimeTable:
                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new timetablereg()).commit();
                        setTitle("Timetable Register");
                        break;
                    case R.id.reg_logout:

                        Intent intent = new Intent(registrationmanager.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_viewStudents:

                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new viewstudents()).commit();
                        setTitle("Student Records");
                        break;
                    case R.id.nav_viewcourse:

                        getSupportFragmentManager().beginTransaction().replace(R.id.reg_container,
                                new viewcourse()).commit();
                        setTitle("Courses");
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }


}

