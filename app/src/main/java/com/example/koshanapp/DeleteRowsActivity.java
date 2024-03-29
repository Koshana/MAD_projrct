package com.example.koshanapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.ArrayList;


public class DeleteRowsActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<UserModel> users=new ArrayList<>();
    static CustomListAdapterDeleteRows deleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_rows);

        try {
            users = UsersDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        deleteAdapter = new CustomListAdapterDeleteRows(this, users);
        listView = (ListView) findViewById(R.id.listviewdeleteID);
        listView.setAdapter(deleteAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Delete info from SQLite");
        }
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
