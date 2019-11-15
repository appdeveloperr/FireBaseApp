package com.example.usmansh.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.usmansh.firebaseapp.R.id.textView1;
import static com.example.usmansh.firebaseapp.R.id.textView2;
import static com.example.usmansh.firebaseapp.R.id.textView3;
import static com.example.usmansh.firebaseapp.R.layout.rowdesign;

public class retdata extends AppCompatActivity {

    public DatabaseReference Dbref;
    public ListView listView;
    ArrayList<String> personlist = new ArrayList<String>();
    ArrayList<String> keylist    = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retdata);

        listView = (ListView)findViewById(R.id.listV);
        //final ArrayAdapter adapter  = new ArrayAdapter(this,android.R.layout.simple_list_item_1,personlist);

        //final adapter adap = new adapter(this,personlist);

        Dbref = FirebaseDatabase.getInstance().getReference();
        //listView.setAdapter(adap);





        FirebaseListAdapter<person> firebaseListAdapter = new FirebaseListAdapter<person>(this,person.class,rowdesign,Dbref)
         {
            @Override
            protected void populateView(View v, person model, int position) {

                TextView tv1 = (TextView) v.findViewById(textView1);
                TextView tv2 = (TextView) v.findViewById(textView2);
                TextView tv3 = (TextView) v.findViewById(textView3);


                //personlist.add(model);
                person per = model;

                tv1.setText(per.getName());
                tv2.setText(per.getContact());
                tv3.setText(per.getAddress());



            }
        };



        listView.setAdapter(firebaseListAdapter);

      /*  Dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(person.class);

                personlist.add(value);
                //String key = dataSnapshot.getKey();
                //keylist.add(key);

               // adap.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(person.class);
                String key = dataSnapshot.getKey();

                int index = keylist.indexOf(key);

                personlist.set(index,value);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

    }
}
