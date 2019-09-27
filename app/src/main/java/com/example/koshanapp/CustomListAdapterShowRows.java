package com.example.koshanapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomListAdapterShowRows extends BaseAdapter {

    Context c;
    ArrayList<UserModel> users;

    public CustomListAdapterShowRows(Context c, ArrayList<UserModel> users) {
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
            view= LayoutInflater.from(c).inflate(R.layout.listviewshow_row,viewGroup,false);
        }

        final TextView meditText1 = (TextView) view.findViewById(R.id.editText1);
        final TextView meditText2 = (TextView) view.findViewById(R.id.editText2);
        final TextView meditText3 = (TextView) view.findViewById(R.id.editText3);
        final TextView meditText4 = (TextView) view.findViewById(R.id.editText4);
        final TextView meditText5 = (TextView) view.findViewById(R.id.editText5);
        final TextView meditText6 = (TextView) view.findViewById(R.id.editText6);
        final TextView meditText7 = (TextView) view.findViewById(R.id.editText7);

        final UserModel user= (UserModel) this.getItem(i);
        meditText1.setText(user.getUsername());
        meditText2.setText(user.getUserphone());
        meditText3.setText(user.getUseremail());
        meditText4.setText(user.getUserexperi());
        meditText5.setText(user.getUserlang());
        meditText6.setText(user.getUserarea());
        meditText7.setText(user.getUserdetail());

        return view;
    }

}
