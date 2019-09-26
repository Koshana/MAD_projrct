package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Reset_Password extends AppCompatActivity {

    EditText email;
    EditText Opassword;
    EditText nPassword;
    EditText CnPassword;
    Button reset;
    Button cancel;
    SQLiteHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);

        email=findViewById(R.id.RlogEmail);
        Opassword=findViewById(R.id.RlogPassword);
        nPassword=findViewById(R.id.RnewPass);
        CnPassword=findViewById(R.id.RCNpass);
        reset=findViewById(R.id.ResetPassword);
        cancel=findViewById(R.id.resetPasswordcancel);
        DB= new SQLiteHelper(this, "test.db",null, 1);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1= email.getText().toString();
                String s2=Opassword.getText().toString();
                String s3=nPassword.getText().toString();
                String s4=CnPassword.getText().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")){

                    Toast.makeText(Reset_Password.this, "Fields are empty", Toast.LENGTH_LONG).show();
                }
                else {

                    if (DB.emailPassword(s1,s2)==true){

                        if (s3.equals(s4)){
                            Boolean resetPassword=DB.resetPassword(s1,s3);

                            if (resetPassword==true){
                                Toast.makeText(Reset_Password.this, "Password reset successfully", Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(Reset_Password.this,Home_activity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Reset_Password.this, "Email not found", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Reset_Password.this, "Password don't match", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(Reset_Password.this, "Your email or current password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                backtoHome();
            }
        });




    }

    public void backtoHome(){

        Intent intent= new Intent(this,Home_activity.class);
        startActivity(intent);
    }
}
