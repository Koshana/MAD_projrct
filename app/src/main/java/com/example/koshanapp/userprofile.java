package com.example.koshanapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class userprofile extends AppCompatActivity {


    String TAG = "userprofile";
    private EditText editDOB;
    private DatePickerDialog.OnDateSetListener DOBSetListener;
    Button update, delete, btn;
    EditText username, birth, email, country, password;
    //com.example.userprofile.SQLiteHelper myDb;
    com.example.koshanapp.SQLiteHelper myDb;
    ImageView userdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);


        email = findViewById(R.id.useremail);
        country = findViewById(R.id.usercountry);
        password = findViewById(R.id.userpass);
        btn = findViewById(R.id.bbb);

        myDb= new SQLiteHelper(this, "test.db",null, 1);
        // myDb=new com.example.userprofile.SQLiteHelper(this);
        delete = findViewById(R.id.delbtn);
        update = findViewById(R.id.upbtn);
        username = findViewById(R.id.uname);
        editDOB = (EditText) findViewById(R.id.dob);
        userdescription = findViewById(R.id.userd);

        editDOB.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);

        userdescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email.getText().toString();

                Intent intent = new Intent(userprofile.this,usermessage.class);

                intent.putExtra("tap",em);

                startActivity(intent);

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);

                builder.setCancelable(true);

                builder.setTitle("Do you want to delete Your account.?");
                //   builder.setMessage("this is message");


                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        builder.setCancelable(false);

                        String s1 = username.getText().toString();

//                       Boolean value = db.deleteprofile(s1);
//
//                        if(value == true ){
//
//
//                            Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(MainActivity.this, NextPage.class);
//                            startActivity(intent);
//
//                        }
                    }
                });

                builder.show();


            }
        });


//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                username.setText("Vinu Wijekoon");
//                editDOB.setText("21/03/1997");
//                email.setText("Vinu@gmail.com");
//                country.setText("SriLanka");
//                password.setText("123");
//
//
//
//
//            }
//        });
        //Update();
        DisplayDate();
        selectData();

    }


//    public void Update(){
//        update.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        boolean isUpdate=myDb.updateData(editID.getText().toString(),
//                                editsnumber.getText().toString(),
//                                editsname.getText().toString(),
//                                editpno.getText().toString(),
//                                editaddress.getText().toString(),
//                                editdob.getText().toString(),
//                                editpname.getText().toString(),
//                                editpnic.getText().toString());
//
//                        if(isUpdate==true){
//                            Toast.makeText(EditStrudent.this,"Data is updated !!",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(EditStrudent.this,"***Data is not updated***",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//    }//end






    public void DisplayDate() {
        editDOB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(userprofile.this,
                                android.R.style.Theme_Black_NoTitleBar,
                                DOBSetListener, year, month, day);

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                }
        );


        DOBSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                editDOB.setText(date);
            }
        };


    }


    //select data
    public void selectData(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = email.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(userprofile.this, "Fields is empty !", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor res = myDb.selectData(s);


                    while (res.moveToNext()) {
                        username.setText(res.getString(1));
                        editDOB.setText(res.getString(2));
                        email.setText(res.getString(3));
                        country.setText(res.getString(4));
                        password.setText(res.getString(5));

                    }

                }
            }
        });
    }//end


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("").setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(userprofile.this,MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }//end


}