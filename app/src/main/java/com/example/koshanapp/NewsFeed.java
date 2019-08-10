package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewsFeed extends AppCompatActivity {

    private Button addPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        addPostBtn=(Button) findViewById(R.id.addPostBtn1);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_guider_details();
            }
        });
    }
    public void open_guider_details(){
        Intent intent =new Intent(this,Post.class);
        startActivity(intent);
    }
}
