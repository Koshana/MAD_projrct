package com.example.koshanapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;


public class AdminActivity extends AppCompatActivity {

    UsersDatabaseAdapter usersDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // create the Database
        usersDatabaseAdapter=new UsersDatabaseAdapter(getApplicationContext());

        CircleMenu circleMenu = findViewById(R.id.circlemenu);

        final String[] menus = {
                "Add Guider",
                "Update Guider",
                "Delete Guider"
        };

        circleMenu.setMainMenu(Color.parseColor("#ff5721"),R.drawable.power,R.drawable.cancelicon)
                .addSubMenu( Color.parseColor("#ff7034"),R.drawable.addusericon)
                .addSubMenu( Color.parseColor("#fe6700"),R.drawable.upateusericon)
                .addSubMenu( Color.parseColor("#ee4328"),R.drawable.deleteusericon)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(AdminActivity.this, "Welcome to " + menus[index] + " Page", Toast.LENGTH_SHORT).show();

                        if (menus[index]=="Add Guider"){
                            AddGuiders();
                        } else if (menus[index]=="Update Guider"){
                            UpdateGuiders();
                        } else if (menus[index]=="Delete Guider"){
                            DeleteGuiders();
                        }

                    }
                });
    }
    // Start the Activities
    private void AddGuiders() {
        Intent i =new Intent(this,InsertRowActivity.class);
        startActivity(i);
    }

    private void UpdateGuiders() {
        Intent i =new Intent(this,UpdateRowsActivity.class);
        startActivity(i);
    }

    private void DeleteGuiders() {
        Intent i =new Intent(this,DeleteRowsActivity.class);
        startActivity(i);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX



}
