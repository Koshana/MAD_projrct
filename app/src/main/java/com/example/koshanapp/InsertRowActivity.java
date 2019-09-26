package com.example.koshanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InsertRowActivity extends AppCompatActivity {

    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mUserEmail;
    private TextView mUserExperi;
    private TextView mUserLang;
    private TextView mUserArea;
    private TextView mUserDetail;
    private Button insertRowFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);

        insertRowFrom = (Button) findViewById(R.id.insertRowFrom);
        mUserName = (TextView) findViewById(R.id.userNameTxt);
        mUserPhone = (TextView) findViewById(R.id.userPhoneTxt);
        mUserEmail = (TextView) findViewById(R.id.userEmailTxt);
        mUserExperi = (TextView) findViewById(R.id.userExperiTxt);
        mUserLang = (TextView) findViewById(R.id.userLangTxt);
        mUserArea = (TextView) findViewById(R.id.userAreaTxt);
        mUserDetail = (TextView) findViewById(R.id.userDetailTxt);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Insert New info in SQLite");
        }
    }
    public void insertRow(View view) {

        TextView userNameTxtView = findViewById(R.id.userNameTxt);
        TextView userPhoneTxtView = findViewById(R.id.userPhoneTxt);
        TextView userEmailTxtView = findViewById(R.id.userEmailTxt);
        TextView userExperiTextView = findViewById(R.id.userExperiTxt);
        TextView userLangTextView = findViewById(R.id.userLangTxt);
        TextView userAreaTextView = findViewById(R.id.userAreaTxt);
        TextView userDetailTextView = findViewById(R.id.userDetailTxt);

        if(userNameTxtView.getText().toString().trim().equals("")
                || userPhoneTxtView.getText().toString().trim().equals("")
                || userEmailTxtView.getText().toString().trim().equals("")
                || userExperiTextView.getText().toString().trim().equals("")
                || userLangTextView.getText().toString().trim().equals("")
                || userAreaTextView.getText().toString().trim().equals("")
                || userDetailTextView.getText().toString().trim().equals("")){
            toast("Please Fill All Fields ");
        }else{
            UsersDatabaseAdapter.insertEntry(userNameTxtView.getText().toString().trim(),userPhoneTxtView.getText().toString(),userEmailTxtView.getText().toString(),userExperiTextView.getText().toString(),userLangTextView.getText().toString(),userAreaTextView.getText().toString(),userDetailTextView.getText().toString());
            Intent myIntent = new Intent(InsertRowActivity.this, AdminActivity.class);
            InsertRowActivity.this.startActivity(myIntent);
            toast("Inserted Successfully");

        }

    }

    private void toast(String nullval) {
        Toast.makeText(getApplicationContext(),String.valueOf(nullval),Toast.LENGTH_LONG).show();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
