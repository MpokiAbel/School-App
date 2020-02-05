package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login , registrar;
    EditText edtUsername,edtPassword;
    schoolAppDB myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        login=(Button)findViewById(R.id.login);
        edtUsername=(EditText)findViewById(R.id.editlogin);
        edtPassword=(EditText)findViewById(R.id.logPassword);
        registrar=findViewById(R.id.RegistrationLink);

        myDB=new schoolAppDB(this);


    }

    @Override
    protected void onResume() {

        super.onResume();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Registration_Category.class);
                startActivity(intent);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    if (edtUsername.getText().toString().isEmpty()) {
                        edtUsername.setError("Enter Registration Number");
                    } if(edtPassword.getText().toString().isEmpty())
                        edtPassword.setError("Enter Password");
                }
                else{

                    String user=edtUsername.getText().toString().trim();
                    String Password=edtPassword.getText().toString().trim();

                    Boolean res1 = myDB.checkUser(user,Password);

                    if (res1 == true){
                        Intent intent =new Intent(MainActivity.this,course_manager.class);
                        intent.putExtra("Registration_number",edtUsername.getText().toString());
                        startActivity(intent);
                    }

                    else
                        Toast.makeText(MainActivity.this, "INVALID USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





    /*

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
*/
}

