package com.example.koshanapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;


public class Post extends AppCompatActivity {


    PlacesClient placesClient;
    ImageView mImageView;
    Button mChooseBtn,mAddBtn,mDisplayBtn;
    EditText edtName,edtPrice;

    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
    public static SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mImageView= findViewById(R.id.image_view);
        mChooseBtn=findViewById(R.id.choose_image_btn);
        mAddBtn=findViewById(R.id.save_image_btn);
        edtName=findViewById(R.id.editText);
        edtPrice=findViewById(R.id.editText2);


        sqLiteHelper= new SQLiteHelper(this, "test.db",null, 1);
        //sqLiteHelper.queryData("DROP TABLE IF EXISTS IMG");
        sqLiteHelper.queryData("create table if not exists IMG (ID integer primary key autoincrement, name varchar, price varchar, image blob)");

        mChooseBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else
                    {
                        pickImageFromGallery();
                    }
                }
                else{

                }
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            imageViewToByte(mImageView)
                    );
                    //Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Post.this, ImageList.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




        //AutoSugeestion

        String apikey="AIzaSyCTV2RcizMI3ZXLkzReHLPjQDpOQUSdEYw";

        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(),apikey);
        }

        placesClient= Places.createClient(this);

        final AutocompleteSupportFragment autocompleteSupportFragment=
                (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.LAT_LNG,Place.Field.NAME));


        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng=place.getLatLng();
                Log.i("PlacesApi", "Place: " + place.getName());
                Log.i("PlacesApi","onPlaceSelected: "+latLng.latitude+"\n"+latLng.longitude);
                edtPrice.setText(place.getName(), TextView.BufferType.NORMAL);

            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });




    }


    public static  byte[] imageViewToByte(ImageView mImageView) {

        Bitmap bitmap= ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;

    }

    private void pickImageFromGallery(){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
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
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //start
            Uri uri = data.getData();
            try{
                InputStream inputStream= getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //end
            //mImageView.setImageURI(data.getData());
            mAddBtn.setEnabled(true);
        }
    }


}
