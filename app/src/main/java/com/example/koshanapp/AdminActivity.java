package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



public class AdminActivity extends AppCompatActivity {

    UsersDatabaseAdapter usersDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // create the instance of Databse

        usersDatabaseAdapter=new UsersDatabaseAdapter(getApplicationContext());
    }

    //open activity to Insert new rows in table
    public void insertRowActivity(View view) {
        Intent myIntent = new Intent(AdminActivity.this, InsertRowActivity.class);
        AdminActivity.this.startActivity(myIntent);
    }

    //Open activity to update rows
    public void updateRowView(View view) {
        Intent myIntent = new Intent(AdminActivity.this, UpdateRowsActivity.class);
        AdminActivity.this.startActivity(myIntent);
    }

    //call method to show rows count in Toast
    public void rowCount(View view) {
        UsersDatabaseAdapter.getRowCount();
    }

    //Open activity to delete rows
    public void deleteRowActivity(View view) {
        Intent myIntent = new Intent(AdminActivity.this, DeleteRowsActivity.class);
        AdminActivity.this.startActivity(myIntent);
    }

    //Button method to truncate table rows
    public void truncateTable(View view) {
        UsersDatabaseAdapter.truncateTable();
    }

    //public void DisplayRow(View view) {
      //  Intent myIntent = new Intent(AdminActivity.this,DisplayActivity.class);
        //AdminActivity.this.startActivity(myIntent);
    //}

    //Open URL in browser
    public void goToUrl (View view) {
        String url = "http://www.google.com";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


}
