package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Post extends AppCompatActivity {

    private Button postbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        addListener();

    }
    public void addListener(){

        postbtn= (Button) findViewById(R.id.postBtn);

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String v= "Posting...";

                Toast.makeText(getApplicationContext(),String.valueOf(v),Toast.LENGTH_LONG).show();
            }
        });


    }
}
