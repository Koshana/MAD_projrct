package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class DetailsGuider extends AppCompatActivity {
    private static RatingBar rate;
    private static TextView value;
    private static Button subBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_guider);

        ListenerForratingBar();
        onButtonClickListener();
    }
    public void ListenerForratingBar(){
        rate =(RatingBar) findViewById(R.id.ratingBar1);
        value=(TextView) findViewById(R.id.ratevalue);

        rate.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                        value.setText(String.valueOf(v));
                        if(v==1.0) {
                            value.setText("Very Bad");
                        }
                        else if(v==2.0){
                            value.setText("Need some Experience");
                        }
                        else if(v==3.0){
                            value.setText("Good");
                        }
                        else if(v==4.0){
                            value.setText("Great");
                        }
                        else if(v==5.0){
                            value.setText("Excellent");
                        }
                    }
                }
        );

    }
    public void onButtonClickListener(){
        rate =(RatingBar) findViewById(R.id.ratingBar1);
        subBtn = (Button) findViewById(R.id.submitRate);


        subBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(DetailsGuider.this,
                                String.valueOf("Submited Successfuly"),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
