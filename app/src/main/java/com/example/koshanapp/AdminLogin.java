package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    Button adminLogBtnA;
    EditText adminUserA;
    EditText adminPassA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUserA=findViewById(R.id.adminUser);
        adminPassA=findViewById(R.id.adminPass);
        adminLogBtnA=(Button) findViewById(R.id.adminLogBtn);

        adminLogBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check Admin Login credentials
                String email=adminUserA.getText().toString().trim();
                String password=adminPassA.getText().toString().trim();
                if (email.equals("admin")&&password.equals("pass")){
                    Start_AdminPage();
                } else {
                    Toast.makeText(AdminLogin.this,"Entered Wrong Credentials!!!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void Start_AdminPage(){
        Intent i =new Intent(this,AdminActivity.class);
        startActivity(i);
        Toast.makeText(AdminLogin.this,"Successfully Logged in", Toast.LENGTH_LONG).show();
    }


}