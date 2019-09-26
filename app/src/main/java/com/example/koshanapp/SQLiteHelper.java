package com.example.koshanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public void insertData(String name, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO IMG VALUES (NULL,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindString(2,price);
        statement.bindBlob(3,image);

        statement.executeInsert();
    }
    public void UpdateData(String name, String price, byte[] image, int id){
        SQLiteDatabase database= getWritableDatabase();

        String sql="UPDATE IMG SET name=? , price=?, image=? where id=?";
        SQLiteStatement statement= database.compileStatement(sql);

        statement.bindString(1,name);
        statement.bindString(2,price);
        statement.bindBlob(3,image);
        statement.bindDouble(4,(double)id);

        statement.execute();
        database.close();

    }
    public void deleteData(int id){
        SQLiteDatabase database= getWritableDatabase();

        String sql= "DELETE FROM IMG WHERE id=?";
        SQLiteStatement statement= database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql,null);
    }

    //Dulmini data ending


    //Koshana DB
    /*
    public SQLiteHelper(@Nullable Context context ) {
        super(context,"test.db",null,1);
    }*/


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //sqLiteDatabase.execSQL("create table user(name text,DOB text,email text primary key,country text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists user");
    }

    public boolean insert(String name, String DOB,String email, String country,String password){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("DOB",DOB);
        contentValues.put("email",email);
        contentValues.put("country",country);
        contentValues.put("password",password);

        long ins=db.insert("user",null,contentValues);

        if (ins == -1){

            return false;
        }
        else
            return true;

    }

    public boolean checkMail(String email){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=?",new String[]{email});
        if (cursor.getCount()>0){
            return  true;
        }
        else
            return false;
    }

    public boolean emailPassword(String email,String password){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});

        if (cursor.getCount()>0){

            return true;
        }
        else
            return false;
    }

    public boolean resetPassword(String email,String password){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("password",password);

        if (!checkMail(email)){
            long ins=db.update("user",contentValues,"email=?",new String[]{String.valueOf(email)});

            return true;
        }
        else
            return false;
    }

    //Koshana End








}
