package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Registration extends AppCompatActivity {

         EditText calendar,myEmail,fname,mname,Lname,pnumber,syear,semister;
         Spinner regionM,districtM,wardM;
         RadioButton selectedRadioButton,categoryStudent,categoryStaff;
         Calendar c;
         Button register;
         RadioGroup radioGroup;
         LinearLayout collection;
         String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        final DBManager dbManager  = new DBManager(this);
        dbManager.open();

        collection=(LinearLayout)findViewById(R.id.RegistrationLayout);
        fname=(EditText)findViewById(R.id.FirstName);
        mname=(EditText)findViewById(R.id.MiddleName);
        Lname=(EditText)findViewById(R.id.LastName);
        pnumber=(EditText)findViewById(R.id.Phone);
        syear=(EditText)findViewById(R.id.StudyYear);
        semister=(EditText)findViewById(R.id.StudySemister);
        regionM=(Spinner) findViewById(R.id.Regions);
        districtM=(Spinner) findViewById(R.id.Districts);
        wardM=(Spinner) findViewById(R.id.Wards);
        categoryStaff=(RadioButton)findViewById(R.id.categoryStaff);
        categoryStudent=(RadioButton)findViewById(R.id.categoryStudent);
        register=(Button)findViewById(R.id.Register) ;
        calendar =(EditText)findViewById(R.id.Date);
        myEmail=(EditText)findViewById(R.id.Email);
        radioGroup=(RadioGroup)findViewById(R.id.Gender);
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


        register.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {

                                            int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();

                                            selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);

                                            if(fname.getText().toString().isEmpty() || mname.getText().toString().isEmpty() ||Lname.getText().toString().isEmpty() || pnumber.getText().toString().isEmpty() || syear.getText().toString().isEmpty()||
                                                    semister.getText().toString().isEmpty() || myEmail.getText().toString().isEmpty() || !myEmail.getText().toString().trim().matches(emailPattern) ||  calendar.getText().toString().isEmpty() ||"-1".equals(String.valueOf(selectedRadioButtonID)))

                                            {
                                           checkEntered(selectedRadioButtonID);
                                       }
                                            else {

                                                dbManager.insert("", fname.getText().toString(), mname.getText().toString(), Lname.getText().toString(), calendar.getText().toString()
                                                        , myEmail.getText().toString(), pnumber.getText().toString(), regionM.getSelectedItem().toString(), districtM.getSelectedItem().toString(), wardM.getSelectedItem().toString(),
                                                        syear.getText().toString(), semister.getText().toString(),selectedRadioButton.getText().toString());
                                            }




                                        }
                                    }

        );









        //First Spinner initialization

       ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource( this ,
                R.array.Region_Arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionM.setAdapter(adapter);


        regionM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item =regionM.getSelectedItem().toString();
                populateDist(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        districtM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item =districtM.getSelectedItem().toString();
                populateWard(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        }







    public void update(){

        String format="dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        calendar.setText(sdf.format(c.getTime()));

    }

    public void populateDist(String district){


        switch (district) {
            case "Dar es Salaam":
                ArrayAdapter<CharSequence> daressalaam;
                daressalaam = ArrayAdapter.createFromResource(this
                        , R.array.Daressalaam, android.R.layout.simple_spinner_item);
                daressalaam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtM.setAdapter(daressalaam);
                break;

            case "Arusha":
                ArrayAdapter<CharSequence> arusha;
                arusha = ArrayAdapter.createFromResource(this
                        , R.array.Arusha, android.R.layout.simple_spinner_item);
                arusha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtM.setAdapter(arusha);
                break;

            case "Dodoma":
                ArrayAdapter<CharSequence> dodoma;
                dodoma = ArrayAdapter.createFromResource(this
                        , R.array.Dodoma, android.R.layout.simple_spinner_item);
                dodoma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtM.setAdapter(dodoma);
                break;

        }

    }

    public void populateWard(String ward){

        switch (ward) {
            case "Ilala":
                ArrayAdapter<CharSequence> ilala;
                ilala = ArrayAdapter.createFromResource(this
                        , R.array.Ilala, android.R.layout.simple_spinner_item);
                ilala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardM.setAdapter(ilala);
                break;

            case "Kinondoni":
                ArrayAdapter<CharSequence> kinondoni;
                kinondoni = ArrayAdapter.createFromResource(this
                        , R.array.Kinondoni, android.R.layout.simple_spinner_item);
                kinondoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardM.setAdapter(kinondoni);
                break;

            case "Temeke":
                ArrayAdapter<CharSequence> temeke;
                temeke = ArrayAdapter.createFromResource(this
                        , R.array.Temeke, android.R.layout.simple_spinner_item);
                temeke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardM.setAdapter(temeke);
                break;






        }



    }

    public void checkEntered(int selectedRadioButtonID) {

            if (fname.getText().toString().isEmpty())
                fname.setError("Enter firstname");
            if (mname.getText().toString().isEmpty())
                mname.setError("Enter middle name");
            if (Lname.getText().toString().isEmpty())
                Lname.setError("Enter last name");
            if (pnumber.getText().toString().isEmpty())
                pnumber.setError("Enter Phone number");
            if (syear.getText().toString().isEmpty())
                syear.setError("Enter Study Year");
            if (semister.getText().toString().isEmpty())
                semister.setError("Enter semister");
            if (myEmail.getText().toString().isEmpty())
                myEmail.setError("EnterEmail Address");
            if (!myEmail.getText().toString().trim().matches(emailPattern))
                myEmail.setError("Invalid Email Address");
            if (calendar.getText().toString().isEmpty())
                calendar.setError("Enter Date of Birth");
            if ("-1".equals(String.valueOf(selectedRadioButtonID)))
               Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();






        }





    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }




}
