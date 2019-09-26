package com.example.koshanapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateImage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_image_activity);


        ImageView imageViewImage = (ImageView) findViewById(R.id.imageViewImage);
        EditText edtName=(EditText) findViewById(R.id.edtName);
        EditText edtPrice=(EditText) findViewById(R.id.edtPrice);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);



    }

}
