package com.example.usmansh.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Usman Sh on 8/25/2017.
 */

public class adapter extends BaseAdapter {

    person per;
    ArrayList<person> personslist = new ArrayList<>();
    Context context;


    public adapter(Context c,ArrayList<person> p){

        context = c;
        personslist = p;
    }


    @Override
    public int getCount() {
        return personslist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View view = new View(context);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if( convertView == null){

            view = layoutInflater.inflate(R.layout.rowdesign,null);

        }else{
            view = convertView;
        }


        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);


        per = personslist.get(position);

        textView1.setText(per.getName());
        textView2.setText(per.getContact());
        textView3.setText(per.getAddress());


        return view;
    }
}
