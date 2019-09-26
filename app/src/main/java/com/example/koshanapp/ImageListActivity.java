package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ImageListActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_list_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_addimage:
                    Intent a = new Intent(ImageListActivity.this, MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.action_profile:
                    Intent c = new Intent(ImageListActivity.this, MainActivity.class);
                    startActivity(c);
                    break;
                case R.id.action_guider:
                    Intent d = new Intent(ImageListActivity.this, MainActivity.class);
                    startActivity(d);
                    break;
            }
            return false;
        }
    };

}
