package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class OurGuiders extends AppCompatActivity {

    private Button vBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_guiders);

        vBtn1=(Button) findViewById(R.id.viewBtn1);
        vBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_guider_details();
            }
        });
    }

    public void open_guider_details(){
        Intent intent =new Intent(this,DetailsGuider.class);
        startActivity(intent);
    }

}
