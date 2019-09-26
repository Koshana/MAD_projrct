package com.example.koshanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

public class UsersDatabaseAdapter {

    static ArrayList<UserModel> users = new ArrayList<>();
    static final String DATABASE_NAME = "UsersDatabase.db";
    static final String TABLE_NAME = "USERS";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + TABLE_NAME + "( ID integer primary key autoincrement,user_name  text,user_phone  text,user_email text, user_experi text, user_lang text, user_area text, user_detail text); ";
    private static final String TAG = "UsersDatabaseAdapter:";

    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;

    public UsersDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public UsersDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close() {
        db.close();
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // method to insert a record in Table
    public static String insertEntry(String user_name, String user_phone, String user_email, String user_experi, String user_lang, String user_area, String user_detail) {

        try {


            ContentValues newValues = new ContentValues();
            // Enter data for each row.
            newValues.put("user_name", user_name);
            newValues.put("user_phone", user_phone);
            newValues.put("user_email", user_email);
            newValues.put("user_experi", user_experi);
            newValues.put("user_lang", user_lang);
            newValues.put("user_area", user_area);
            newValues.put("user_detail", user_detail);


            // Insert One user detail into your table
            db = dbHelper.getWritableDatabase();
            long result = db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            db.close();

        } catch (Exception ex) {
        }
        return "ok";
    }


    // method to get all Rows Saved in Table
    public static ArrayList<UserModel> getRows() throws JSONException {

        users.clear();
        UserModel user;
        db = dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (projCursor.moveToNext()) {

            user = new UserModel();
            user.setID(projCursor.getString(projCursor.getColumnIndex("ID")));
            user.setUsername(projCursor.getString(projCursor.getColumnIndex("user_name")));
            user.setUserphone(projCursor.getString(projCursor.getColumnIndex("user_phone")));
            user.setUseremail(projCursor.getString(projCursor.getColumnIndex("user_email")));
            user.setUserexperi(projCursor.getString(projCursor.getColumnIndex("user_experi")));
            user.setUserlang(projCursor.getString(projCursor.getColumnIndex("user_lang")));
            user.setUserdetail(projCursor.getString(projCursor.getColumnIndex("user_detail")));
            users.add(user);
        }
        projCursor.close();
        return users;
    }

    // method to delete a Record in Tbale using Primary Key Here it is ID
    public static int deleteEntry(String ID) {
        String where = "ID=?";
        int numberOFEntriesDeleted = db.delete(TABLE_NAME, where, new String[]{ID});
        return numberOFEntriesDeleted;
    }

    // method to get Count of Toatal Rows in Table
    public static int getRowCount() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        db.close();
        return cursor.getCount();
    }

    // method to Truncate/ Remove All Rows in Table
    public static void truncateTable() {
        db = dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
    }

    // method to Update an Existing Row in Table
    public static void updateEntry(String ID, String Username, String Userphone, String Useremail, String Userexperi, String Userlang, String Userarea, String Userdetail) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("user_name", Username);
        updatedValues.put("user_phone", Userphone);
        updatedValues.put("user_email", Useremail);
        updatedValues.put("user_experi", Userexperi);
        updatedValues.put("user_lang", Userlang);
        updatedValues.put("user_area", Userarea);
        updatedValues.put("user_detail", Userdetail);
        String where = "ID = ?";
        db = dbHelper.getReadableDatabase();
        db.update(TABLE_NAME, updatedValues, where, new String[]{ID});
        db.close();

    }


    public Cursor getAllPerson() {

        Log.d("Person_helper", "Reading person record");

        db = dbHelper.getWritableDatabase();
        String query = "select * from " + TABLE_NAME;

        Cursor res = db.rawQuery(query, null);

        return res;

    }

    private static void toast(String inform) {

        // Toast.makeText(getApplicationContext(),String.valueOf(inform),Toast.LENGTH_SHORT).show();
    }
}