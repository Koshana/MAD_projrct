package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Guid_Registration extends AppCompatActivity {

    private Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid__registration);

        regBtn=(Button) findViewById(R.id.buttonReg);
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
