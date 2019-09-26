package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_registration extends AppCompatActivity {

    EditText name;
    EditText DOB;
    EditText email;
    EditText country;
    EditText password;
    Button reg;
    Button cancel;
    SQLiteHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        DB= new SQLiteHelper(this, "test.db",null, 1);
        DB.queryData("create table if not exists user(name text,DOB text,email text primary key,country text,password text)");

        //DB= new SQLiteHelper(this);
        name= findViewById(R.id.regName);
        DOB = findViewById(R.id.regDOB);
        email= findViewById(R.id.regEmail);
        country= findViewById(R.id.regCountry);
        password= findViewById(R.id.regPass);
        reg=findViewById(R.id.buttonReg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checkDataEntered();
                String s1= name.getText().toString();
                String s2=DOB.getText().toString();
                String s3=email.getText().toString();
                String s4=country.getText().toString();
                String s5=password.getText().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")){

                    Toast.makeText(User_registration.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{

                    if (DB.checkMail(s3)==false){

                        Boolean insert= DB.insert(s1,s2,s3,s4,s5);

                        if (insert==true){

                            Toast.makeText(User_registration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent= new Intent(User_registration.this,Home_activity.class);
                            startActivity(intent);
                        }
                    }
                    else {

                        Toast.makeText(User_registration.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    }


                }



            }
        });





    }

   /* public boolean isEmail(EditText text){

        CharSequence email= text.getText().toString();
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean isEmpty(EditText text){

        CharSequence str= text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkDataEntered(){

        if (isEmpty(name)){

            name.setError("You must enter your name");
        }

        if (isEmpty(DOB)){

            DOB.setError("You must enter your DOB");
        }

        if (isEmpty(email)){

            email.setError("You must enter your email");
        }else if (isEmail(email)){

            email.setError("Invalid email");
        }

        if (isEmpty(country)){

            country.setError("You must enter your country");
        }

        if (isEmpty(password)){

            password.setError("You must enter a password");
        }
    }*/

    public void backTohome(){


        Intent intent= new Intent(this,Home_activity.class);
        startActivity(intent);
    }
}
