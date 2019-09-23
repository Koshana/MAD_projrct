package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class Home_activity extends AppCompatActivity {



    private Button button;
    private Button button1;
    private Button button2;
    private Button button4;
    private Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);


        button=(Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Start_homepage();
            }
        });

        button2=(Button) findViewById(R.id.RegiGuid);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Start_Guid_page();
            }
        });



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

        button5=(Button) findViewById(R.id.admin);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_admin();
            }
        });


    }


    public void Start_homepage(){

        Intent i =new Intent(this,DefaultActivity.class);
        startActivity(i);

    }


    public void start_User_registratin(){

        Intent i =new Intent(this,User_registration.class);
        startActivity(i);

    }

    public void Start_Guid_page(){

        Intent i =new Intent(this,Guid_Registration.class);
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


}
