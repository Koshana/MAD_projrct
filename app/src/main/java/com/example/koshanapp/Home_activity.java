package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home_activity extends AppCompatActivity {

    private Button button;
    private Button button1;
    private Button button2;
    private Button button4;
    Button adminPageBtn;
    EditText logEmail;
    EditText logPass;
    SQLiteHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);


        button=(Button) findViewById(R.id.login);
        logEmail=findViewById(R.id.RlogEmail);
        logPass=findViewById(R.id.RlogPassword);
        DB= new SQLiteHelper(this, "test.db",null, 1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checkLoginDetails();
                String email=logEmail.getText().toString();
                String password=logPass.getText().toString();
                if (DB.emailPassword(email,password)==true){

                    Start_homepage();
                    Toast.makeText(Home_activity.this, "Successfully Logged in", Toast.LENGTH_LONG).show();

                }
                else{

                    Toast.makeText(Home_activity.this, "Wrong email or Password", Toast.LENGTH_LONG).show();
                }


            }
        });

        /*button2=(Button) findViewById(R.id.RegiGuid);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Start_Guid_page();
            }
        });*/



        button1=(Button)findViewById(R.id.RegiUser);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_User_registratin();
            }
        });

        button4=(Button) findViewById(R.id.ResetPassword);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Start_resetPassword_page();
            }
        });

        adminPageBtn = (Button) findViewById(R.id.adminPageBtn);
        adminPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAdminLoginPage();
            }
        });


    }


    public void Start_homepage(){

        Intent i =new Intent(this,ImageList.class);
        startActivity(i);

    }


    public void start_User_registratin(){

        Intent i =new Intent(this,User_registration.class);
        startActivity(i);

    }

    public void startAdminLoginPage(){

        Intent i =new Intent(this,AdminLogin.class);
        startActivity(i);

    }

    public void Start_resetPassword_page(){

        Intent i =new Intent(this,Reset_Password.class);
        startActivity(i);

    }

    public void start_admin(){
        Intent i = new Intent(this,AdminActivity.class);
        startActivity(i);
    }

    /*public void checkLoginDetails(){


    }*/


}