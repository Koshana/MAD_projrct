package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Guid_Registration extends AppCompatActivity {

    private Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid__registration);

        regBtn=(Button) findViewById(R.id.button);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_guider_details();
            }
        });

    }
    public void open_guider_details(){
        Intent intent =new Intent(this,OurGuiders.class);
        startActivity(intent);
    }
}
