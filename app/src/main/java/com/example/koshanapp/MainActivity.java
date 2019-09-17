package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tx=(TextView) findViewById(R.id.ed);
        //Typeface customerfont = Typeface.createFromAsset(getAssets(),"Sweet Hipster.ttf");
        //tx.setTypeface(customerfont);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Home_activity.class);
                startActivity(intent);
                finish();

            }
        },2500);

    }


}
