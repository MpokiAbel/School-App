package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Password_Fragment extends Fragment {


    EditText currentPass,newPass,confirmedPass;
    String dbPass;
    Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentPass=view.findViewById(R.id.currentPassword);
        newPass=view.findViewById(R.id.newpassword);
        confirmedPass=view.findViewById(R.id.confirmedPass);
        button=view.findViewById(R.id.imageButton);

        final  course_manager take=(course_manager)getActivity();

        final String regex = "^[a-zA-Z0-9]+$";


        Cursor cursor=take.password(take.Regno);

            while (cursor.moveToNext()){

                dbPass=cursor.getString(0);


            }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPass.getText().toString().equals(dbPass)) {
                    if (newPass.getText().toString().length()>7 && newPass.getText().toString().matches(regex)){

                        if (newPass.getText().toString().equals(confirmedPass.getText().toString())){


                            take.update(newPass.getText().toString(), take.Regno);
                            currentPass.setText("");
                            newPass.setText("");
                            confirmedPass.setText("");
                            Toast.makeText(take,"Password updated",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            confirmedPass.setError("Passwords do not match");
                        }


                    }else
                        newPass.setError("Your password is too short and should contain 1-9 a-z or A-Z");


                }

                else {

                    currentPass.setError("Passwords is Incorrect");




                }





            }
        });



    }








}
