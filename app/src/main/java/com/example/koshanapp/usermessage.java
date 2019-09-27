package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class usermessage extends AppCompatActivity {

    SQLiteHelper db;
    Button insert;
    EditText e1,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermessage);

        e1 = findViewById(R.id.edit);
        insert = findViewById(R.id.ins);
        mail = findViewById(R.id.umail);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usmail = mail.getText().toString();
                String descript = e1.getText().toString();

                boolean data = db.insertdescription(usmail,descript);

                if (data== true){


                    Toast.makeText(usermessage.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(usermessage.this,Post.class);
                    startActivity(intent);


                } else

                    Toast.makeText(usermessage.this, "Error", Toast.LENGTH_SHORT).show();



            }
        });



        Intent intent = getIntent();


        String mes1 = intent.getStringExtra("tap");

        mail.setText(mes1);

    }


}
