package com.example.koshanapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListAdapter extends BaseAdapter {


    public ImageListAdapter(Context context, int layout, ArrayList<Image> imageList) {
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    private Context context;
    private int layout;
    private ArrayList<Image> imageList;

    @Override
    public int getCount() {
        return imageList.size();
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;

    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        ViewHolder holder1= new ViewHolder();

        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder1.txtName=(TextView) row.findViewById(R.id.TV1);
            holder1.txtPrice=(TextView) row.findViewById(R.id.TV2);
            holder1.imageView=(ImageView) row.findViewById(R.id.imageView2);
            row.setTag(holder1);
        }
        else{
            holder1=(ViewHolder) row.getTag();
        }

        Image image=imageList.get(position);
        System.out.println("===================="+image.getName());
        String n = image.getName();
        System.out.println("===================="+image.getPrice());

        holder1.txtName.setText(n);
        holder1.txtPrice.setText(image.getPrice());

        byte[] imageImage = image.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageImage,0,imageImage.length);
        holder1.imageView.setImageBitmap(bitmap);

        return row;
    }
}
