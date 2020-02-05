package com.example.schoolapp;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class staffreg extends Fragment {

    EditText calendar,myEmail,fname,mname,Lname,pnumber,syear,semister;
    Spinner regionM,districtM,wardM;
    RadioButton selectedRadioButton;
    Calendar c;
    Button register;
    RadioGroup radioGroup;
    RelativeLayout collection;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    schoolAppDB myDB;
    int year = Calendar.getInstance().get(Calendar.YEAR);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.staffreg,container,false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDB  = new schoolAppDB(getContext());
        collection=view.findViewById(R.id.StaffRegistrationLayout);
        fname=view.findViewById(R.id.StaffFirstName);
        mname=view.findViewById(R.id.StaffMiddleName);
        Lname=view.findViewById(R.id.StaffLastName);
        pnumber=view.findViewById(R.id.StaffPhone);
        regionM=view.findViewById(R.id.StaffRegions);
        districtM=view.findViewById(R.id.StaffDistricts);
        wardM=view.findViewById(R.id.StaffWards);
        register=view.findViewById(R.id.StaffRegister) ;
        calendar =view.findViewById(R.id.StaffDate);
        myEmail=view.findViewById(R.id.StaffEmail);
        radioGroup=view.findViewById(R.id.StaffGender);
        c=Calendar.getInstance();
        final Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.StaffRegistration), R.string.confirm, Snackbar.LENGTH_SHORT);


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
                new DatePickerDialog(getActivity(), date, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        register.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v) {
                                           String s = "password";

                                           int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();

                                           selectedRadioButton = view.findViewById(selectedRadioButtonID);

                                           if(fname.getText().toString().isEmpty() || mname.getText().toString().isEmpty() ||Lname.getText().toString().isEmpty() || pnumber.getText().toString().isEmpty() || myEmail.getText().toString().isEmpty() || !myEmail.getText().toString().trim().matches(emailPattern) ||  calendar.getText().toString().isEmpty() ||"-1".equals(String.valueOf(selectedRadioButtonID)))

                                           {
                                               checkEntered(selectedRadioButtonID);
                                           }
                                           else {

                                               int number = gen();
                                               String registrationNumber =String.valueOf(number);

                                               Boolean res = myDB.insertstaff(registrationNumber, fname.getText().toString().trim(), mname.getText().toString().trim(), Lname.getText().toString().trim(), calendar.getText().toString().trim(),
                                                       myEmail.getText().toString().trim(), pnumber.getText().toString().trim(), regionM.getSelectedItem().toString(), districtM.getSelectedItem().toString(), wardM.getSelectedItem().toString(),
                                                       selectedRadioButton.getText().toString(), s);


                                               if (res == true) {
                                                   mySnackbar.show();

                                               } else
                                                   Toast.makeText(getActivity(), "SOMETHING WENT WRONG", Toast.LENGTH_LONG).show();



                                           }




                                       }
                                   }

        );

        //First Spinner initialization

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource( getContext() ,
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


    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }



    public void populateDist(String district){


        switch (district) {
            case "Dar es Salaam":
                ArrayAdapter<CharSequence> daressalaam;
                daressalaam = ArrayAdapter.createFromResource(getContext()
                        , R.array.Daressalaam, android.R.layout.simple_spinner_item);
                daressalaam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtM.setAdapter(daressalaam);
                break;

            case "Arusha":
                ArrayAdapter<CharSequence> arusha;
                arusha = ArrayAdapter.createFromResource(getContext()
                        , R.array.Arusha, android.R.layout.simple_spinner_item);
                arusha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtM.setAdapter(arusha);
                break;

            case "Dodoma":
                ArrayAdapter<CharSequence> dodoma;
                dodoma = ArrayAdapter.createFromResource(getContext()
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
                ilala = ArrayAdapter.createFromResource(getContext()
                        , R.array.Ilala, android.R.layout.simple_spinner_item);
                ilala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardM.setAdapter(ilala);
                break;

            case "Kinondoni":
                ArrayAdapter<CharSequence> kinondoni;
                kinondoni = ArrayAdapter.createFromResource(getContext()
                        , R.array.Kinondoni, android.R.layout.simple_spinner_item);
                kinondoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardM.setAdapter(kinondoni);
                break;

            case "Temeke":
                ArrayAdapter<CharSequence> temeke;
                temeke = ArrayAdapter.createFromResource(getContext()
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
        if (myEmail.getText().toString().isEmpty())
            myEmail.setError("EnterEmail Address");
        if (!myEmail.getText().toString().trim().matches(emailPattern))
            myEmail.setError("Invalid Email Address");
        if (calendar.getText().toString().isEmpty())
            calendar.setError("Enter Date of Birth");
        if ("-1".equals(String.valueOf(selectedRadioButtonID)))
            Toast.makeText(getContext(), "Select Gender", Toast.LENGTH_SHORT).show();
    }


    public void update(){

        String format="dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        calendar.setText(sdf.format(c.getTime()));

    }
}
