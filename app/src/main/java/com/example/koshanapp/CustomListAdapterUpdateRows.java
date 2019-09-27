package com.example.koshanapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

class CustomListAdapterUpdateRows extends BaseAdapter {

    Context c;
    ArrayList<UserModel> users;

    public CustomListAdapterUpdateRows(Context c, ArrayList<UserModel> users) {
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
            view= LayoutInflater.from(c).inflate(R.layout.listviewupdate_row,viewGroup,false);
        }

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        final EditText meditText3 = (EditText) view.findViewById(R.id.editText3);
        final EditText meditText4 = (EditText) view.findViewById(R.id.editText4);
        final EditText meditText5 = (EditText) view.findViewById(R.id.editText5);
        final EditText meditText6 = (EditText) view.findViewById(R.id.editText6);
        final EditText meditText7 = (EditText) view.findViewById(R.id.editText7);
        Button updateBtn = (Button) view.findViewById(R.id.updateBtn);

        final UserModel user= (UserModel) this.getItem(i);
        meditText1.setText(user.getUsername());
        meditText2.setText(user.getUserphone());
        meditText3.setText(user.getUseremail());
        meditText4.setText(user.getUserexperi());
        meditText5.setText(user.getUserlang());
        meditText6.setText(user.getUserarea());
        meditText7.setText(user.getUserdetail());

        updateBtn.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                final Context c = v.getContext();
                final AlertDialog.Builder alertbox = new AlertDialog.Builder(c);
                alertbox.setCancelable(true);
                alertbox.setTitle("Are you Sure?");
                alertbox.setMessage("If you need to update Press Yes");
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String col1value = meditText1.getText().toString();
                        String col2value = meditText2.getText().toString();
                        String col3value = meditText3.getText().toString();
                        String col4value = meditText4.getText().toString();
                        String col5value = meditText5.getText().toString();
                        String col6value = meditText6.getText().toString();
                        String col7value = meditText7.getText().toString();
                        UsersDatabaseAdapter.updateEntry(user.getID(),col1value,col2value,col3value,col4value,col5value,col6value,col7value);
                        Toast.makeText(c,"Guider Info Updated Successfully", Toast.LENGTH_LONG).show();
                    }
                });
                alertbox.create();
                alertbox.show();

            }
        });

        return view;
    }


}
