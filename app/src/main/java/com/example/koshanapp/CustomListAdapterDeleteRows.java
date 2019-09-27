package com.example.koshanapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class CustomListAdapterDeleteRows extends BaseAdapter {

    Context c;
    ArrayList<UserModel> users;

    public CustomListAdapterDeleteRows(Context c, ArrayList<UserModel> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listviewdelete_row,viewGroup,false);
        }

        TextView mtextView3 = (TextView) view.findViewById(R.id.textView3);
        Button deleteBtn = (Button) view.findViewById(R.id.button1);

        final UserModel user= (UserModel) this.getItem(i);
        mtextView3.setText(user.getUseremail());



        deleteBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final Context c = v.getContext();
                final AlertDialog.Builder alertbox = new AlertDialog.Builder(c);
                alertbox.setCancelable(true);
                alertbox.setTitle("Are you Sure?");
                alertbox.setMessage("Cannot revert!.If you need to delete Press Yes");
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UsersDatabaseAdapter.deleteEntry(user.getID());
                        Toast.makeText(c,"Guider Info Deleted Successfully", Toast.LENGTH_LONG).show();
                        users.remove(i);
                        notifyDataSetChanged();
                    }
                });

                alertbox.create();
                alertbox.show();

            }
        });

        return view;
    }
}
