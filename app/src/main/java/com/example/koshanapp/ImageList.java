package com.example.koshanapp;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Image> list;
    ImageListAdapter adapter = null;
    private TextView mTextMessage;
    public SQLiteHelper sqLiteHelper = new SQLiteHelper(this,"test.db",null, 1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_list_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        gridView= (GridView) findViewById(R.id.gridView);
        list= new ArrayList<>();
        adapter= new ImageListAdapter(this, R.layout.img_items,list);
        gridView.setAdapter(adapter);
        //order by id desc
        //sqLiteHelper.queryData("DROP TABLE IF EXISTS IMG");
        Cursor cursor= sqLiteHelper.getData("SELECT * FROM IMG ");
        list.clear();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String price=cursor.getString(2);
            byte [] image=cursor.getBlob(3);

            list.add(new Image(id, name,price,image));
        }
        adapter.notifyDataSetChanged();
        //from here
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {


                CharSequence[] items = {"Update", "Delete"};
                //System.out.println("=================");
                AlertDialog.Builder dialog = new AlertDialog.Builder(ImageList.this, R.style.CustomDialogTheme);
                dialog.setTitle("Choose an action");
                dialog.show();
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {

                            Cursor c= sqLiteHelper.getData("SELECT id FROM IMG");
                            ArrayList<Integer> arrID= new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }


                            showDialogpdate(ImageList.this,arrID.get(position));
                            //Toast.makeText(getApplicationContext(), "Update...", Toast.LENGTH_LONG).show();
                        } else {

                            Cursor c= sqLiteHelper.getData("SELECT id FROM IMG");
                            ArrayList<Integer> arrID= new ArrayList<Integer>();
                            while(c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                           // Toast.makeText(getApplicationContext(), "Delete...", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                dialog.show();
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ImageList.this, Post.class);
                startActivity(a);
            }
        });
    }



    ImageView imageViewImage;
    Button btnUpdate;

    public void showDialogpdate(Activity activity, final int position){


        final Dialog dialog= new Dialog(activity);
        dialog.setContentView(R.layout.update_image_activity);
        dialog.setTitle("Update");

        imageViewImage = (ImageView) dialog.findViewById(R.id.imageViewImage);
        final EditText edtName=(EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice=(EditText) dialog.findViewById(R.id.edtPrice);
        btnUpdate= (Button) dialog.findViewById(R.id.btnUpdate);

        //from


        Cursor cursor= sqLiteHelper.getData("SELECT * FROM IMG WHERE id ="+position);
        String name;

        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(0);
                name=cursor.getString(1);
                edtName.setText(name);
                String price=cursor.getString(2);
                edtPrice.setText(price);
                byte [] image=cursor.getBlob(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                imageViewImage.setImageBitmap(bitmap);
            } while (cursor.moveToNext());
        }




//here

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();


        imageViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        ImageList.this,
                        new String []{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                            sqLiteHelper.UpdateData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            Post.imageViewToByte(imageViewImage),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Updated Succesfully!!!",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Log.e("Update Error",e.getMessage());
                }
                updateImageList();
            }
        });

        //to here
    }

    public void updateImageList(){
        Cursor cursor= sqLiteHelper.getData("SELECT * FROM IMG");
        list.clear();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String price=cursor.getString(2);
            byte [] image=cursor.getBlob(3);

            list.add(new Image(id, name,price,image));
        }
        adapter.notifyDataSetChanged();
    }


    private void showDialogDelete(final int idImage){
        //AlertDialog dialog = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ImageList.this, R.style.CustomDialogTheme);
        dialogDelete.setTitle("Warning!!!");
        dialogDelete.setMessage("Are you sure you want to delete this image!");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    sqLiteHelper.deleteData(idImage);
                    Toast.makeText(getApplicationContext(),"Deleted Successfully!",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Log.e("Error",e.getMessage());
                }
                updateImageList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void pickImageFromGallery(){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 888);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 888:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else{
                    Toast.makeText(this, "permisson denied",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 888) {
            //start
            Uri uri = data.getData();
            try{
                InputStream inputStream= getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageViewImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //end
            //mImageView.setImageURI(data.getData());
            btnUpdate.setEnabled(true);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_addimage:
                    Intent a = new Intent(ImageList.this, Post.class);
                    startActivity(a);
                    break;
                case R.id.action_profile:
                    Intent c = new Intent(ImageList.this, Post.class);
                    startActivity(c);
                    break;
                case R.id.action_guider:
                    Intent d = new Intent(ImageList.this, OurGuiders.class);
                    startActivity(d);
                    break;
            }
            return false;
        }
    };



}
